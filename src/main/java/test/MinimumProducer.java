package test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.LocalDateTime;
import java.util.Properties;

public class MinimumProducer {
    public static final String TOPIC="TEST_TOPIC";

    public static void main(String[] args) throws InterruptedException {
        Properties properties = getProperties();
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);
        while(true){
            Thread.sleep(500);
            producer.send(new ProducerRecord<String, String>(TOPIC,"Message sent at time: "+ LocalDateTime.now()));
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","euve35533.startvps.com:2181");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }
}
