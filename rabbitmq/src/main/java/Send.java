import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.util.List;


public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {

        MonitoredData md = new MonitoredData();
        List<MonitoredData> list = md.readData( "activity.txt" );
        //System.out.println(list.get(0).toString());
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try( Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //String message = "Hello World!";
            for(MonitoredData m: list)
            {
                channel.basicPublish("", QUEUE_NAME, null, m.toJSONString().getBytes());
                Thread.sleep(1000);
            }

            System.out.println(" [x] Send '" + list.toString() + "'");

        }
    }
}
