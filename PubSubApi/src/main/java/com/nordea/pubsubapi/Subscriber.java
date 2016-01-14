/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordea.pubsubapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *
 * @author Tore
 */
public class Subscriber extends Connector {

    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(getProperties());

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "euve35533.startvps.com:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "trace_consumer");
        return properties;
    }

    public ArrayList<String> getMessages(String topic) {
        ArrayList<String> list = new ArrayList();
        consumer.subscribe(Arrays.asList(topic));
        ConsumerRecords<String, String> records = consumer.poll(250);
        for (ConsumerRecord<String, String> record : records) {
            String value = record.value();
            System.out.println(value);
            list.add(value);
        }
        return list;
    }
}
