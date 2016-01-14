/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordea.pubsubapi;

import static java.lang.System.getProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author Tore
 */
public class Publisher extends Connector {

    KafkaProducer<String, String> producer = new KafkaProducer<String, String>(getProperties());

    public void send(String topic, String message) {
        producer.send(new ProducerRecord<String, String>(topic, message));
    }

}
