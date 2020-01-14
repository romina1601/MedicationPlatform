package com.rabbitmq;

import com.rabbitmq.MonitoredData;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class Send {

    private final static String QUEUE_NAME = "hello";

    public void sendData() throws Exception {

        System.out.println("Started");
        MonitoredData md = new MonitoredData();
        /*File f = new File("activity.txt");
        if(f.exists())
            System.out.println("Ok");
        else
            System.out.println("Not ok");*/
        List<MonitoredData> list = md.readData( "activity.txt" );
        //System.out.println(list.get(0).toString());
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.102");
        //factory.setHost("localhost");
        try( Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            for(MonitoredData m: list)
            {
                System.out.println("Sent: " + m.toString());
                channel.basicPublish("", QUEUE_NAME, null, m.toJSONString().getBytes());
                Thread.sleep(1000);
            }

            System.out.println(" [x] com.rabbitmq.Send '" + list.toString() + "'");

        }
    }
}
