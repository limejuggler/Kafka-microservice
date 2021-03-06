package test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by g47602 on 12/01/2016.
 */
public class MinimumConsumer09 {
    public static void main(String[] args) {
        Properties properties = getProperties();
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(MinimumProducer09.TOPIC));
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(250);
            for(ConsumerRecord<String,String> record:records){
                System.out.println(record.value());
            }
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","euve35533.startvps.com:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id","trace_consumer");
        return properties;
    }
}