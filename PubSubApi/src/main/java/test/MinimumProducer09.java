package test;

import com.nordea.pubsubapi.Publisher;
import java.util.Properties;

public class MinimumProducer09 {

    public static void main(String[] args) throws InterruptedException {
        Publisher pub = new Publisher();
        while (true) {            
            pub.send(Publisher.default_topic, "Hello world!");
            Thread.sleep(500);
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "euve35533.startvps.com:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }
}
