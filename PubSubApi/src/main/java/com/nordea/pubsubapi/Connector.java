/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordea.pubsubapi;

import java.util.Properties;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 *
 * @author Tore
 */
public class Connector {

    KafkaProducer<String,String> producer = new KafkaProducer<String, String>(getProperties());
    KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(getProperties());    
    
    public static String default_topic = "defaultTopic";
    
    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "euve35533.startvps.com:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "trace_consumer");
        return properties;
    }
}
