package test;

import com.nordea.pubsubapi.Subscriber;
import java.util.ArrayList;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by g47602 on 12/01/2016.
 */
public class MinimumConsumer09 {

    public static void main(String[] args) {
        Subscriber sub = new Subscriber();
        while (true) {
            try {
                final ArrayList<String> messages = sub.getMessages(Subscriber.default_topic);
                Thread.sleep(500);
                for (String message : messages) {
                    System.out.println(message);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MinimumConsumer09.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "euve35533.startvps.com:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "trace_consumer");
        return properties;
    }
}
