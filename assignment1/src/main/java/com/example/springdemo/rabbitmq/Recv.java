package com.example.springdemo.rabbitmq;

import com.example.springdemo.entities.MonitoredData;
import com.example.springdemo.entities.Patient;
import com.example.springdemo.repositories.PatientRepository;
import com.example.springdemo.services.CaregiverService;
import com.example.springdemo.services.MonitoredDataService;
import com.example.springdemo.services.PatientService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@CrossOrigin
public class Recv {

    private final static String QUEUE_NAME = "hello";
    private MonitoredDataService monitoredDataService;
    private CaregiverService caregiverService;
    private PatientService patientService;
    private PatientRepository patientRepository;

    // for websocket
    private final SimpMessagingTemplate template;

    @Autowired
    Recv(MonitoredDataService monitoredDataService, CaregiverService caregiverService,
         PatientService patientService,
         PatientRepository patientRepository,
         SimpMessagingTemplate template){
     this.monitoredDataService = monitoredDataService;
     this.caregiverService = caregiverService;
     this.patientService = patientService;
     this.patientRepository = patientRepository;
     this.template = template;
    }


    @RabbitListener(queues = QUEUE_NAME)
    public void run() throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.43.97");
        //factory.setHost("192.168.1.102");
        //factory.setHost("localhost");
        Connection connection = factory.newConnection();
        //System.out.println(connection.getServerProperties());
        Channel channel = connection.createChannel();
        JSONParser jsonParser = new JSONParser();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            try{

                String validation = "";
                String alert = "";

                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                DateFormat format = new SimpleDateFormat( "EEE MMM d HH:mm:ss zzz yyyy");
                JSONObject jsonObject = (JSONObject) jsonParser.parse(message);
                MonitoredData monitoredData = new MonitoredData(
                        Integer.parseInt(jsonObject.get("patient_id").toString()),
                        jsonObject.get("activity").toString(),
                        format.parse(jsonObject.get("start_time").toString()),
                        format.parse(jsonObject.get("end_time").toString()),
                        ""
                );

                Integer patientId = monitoredData.getPatientId();
                System.out.println(patientId);
                String caregiverUsername="";

                List<Patient> allPatients = this.patientRepository.getAllFetch2();
                for(Patient p: allPatients)
                {
                    if(p.getPatientId() == patientId)
                    {
                        caregiverUsername = p.getCaregiver().getUser().getUsername();
                        System.out.println("Caregiver username is: " + caregiverUsername);
                    }
                }


                int check = checkRules(monitoredData);

                switch (check) {
                    case 1:
                        validation = "Too much sleep";
                        alert = "ALERT! Patient with ID: " + monitoredData.getPatientId() + " has slept too much!";
                        monitoredData.setBehavior("NOT NORMAL");
                        break;
                    case 2:
                        validation = "Too much leaving";
                        alert = "ALERT! Patient with ID: " + monitoredData.getPatientId() + " has been out for too long!";
                        monitoredData.setBehavior("NOT NORMAL");
                        break;
                    case 3:
                        validation = "Too much toileting";
                        alert = "ALERT! Patient with ID: " + monitoredData.getPatientId() + " has been in " +
                                "the bathroom for too long!";
                        monitoredData.setBehavior("NOT NORMAL");
                        break;
                    case 0:
                        validation = "OK";
                        monitoredData.setBehavior("NORMAL");
                        break;
                }

                System.out.println("Behavior for this is: " + monitoredData.getBehavior());
                this.monitoredDataService.insert(monitoredData);
                System.out.println(" [x] Received '" + monitoredData.toString() + "'");

                System.out.println(validation);
                System.out.println(alert);



                onReceivedMessage(alert, caregiverUsername);


            }
            catch (ParseException | java.text.ParseException e)
            {
                System.out.println(e.getMessage());
            }


        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consuemrTag -> { });
    }

    public Integer checkRules(MonitoredData monitoredData)
    {

        //gives the duration in milliseconds
        int duration = (int) (monitoredData.getEndTime().getTime() - monitoredData.getStartTime().getTime());

        int seconds = duration /1000 %60;
        int minutes = duration /1000 / 60 % 60;
        int hours = duration / 1000 / 3600;

        switch (monitoredData.getActivity()) {
            case "Sleeping":
                if (hours > 12 || (hours == 12 && (minutes > 0 || seconds > 0))) {
                    return 1;
                }
                break;
            case "Leaving":
                if (hours > 12 || (hours == 12 && (minutes > 0 || seconds > 0))) {
                    return 2;
                }
                break;
            case "Toileting":
            case "Showering":
            case "Grooming":
                if (hours > 1 || (hours == 1 && (minutes > 0 || seconds > 0))) {
                    return 3;
                }
                break;
            default:
                return 0;

        }

        return 0;
    }

    //@MessageMapping("/send/message")
    public void onReceivedMessage(String message, String caregiverUsername)
    {
        String url = "/topic/" + caregiverUsername;
        this.template.convertAndSend(url, message);
    }
}
