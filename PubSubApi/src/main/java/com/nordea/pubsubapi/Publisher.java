/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordea.pubsubapi;

import static java.lang.System.getProperties;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author Tore
 */
public class Publisher extends Connector {

    KafkaProducer<String, String> producer = new KafkaProducer<String, String>(getProperties());

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "euve35533.startvps.com:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

    public void send(String topic, String message) {
        producer.send(new ProducerRecord<String, String>(topic, message));
    }

}
