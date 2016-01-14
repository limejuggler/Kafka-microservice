/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordea.pubsubapi;

import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author Tore
 */
public class Publisher extends Connector {
    
    public void send(String topic, String message)
    {
        producer.send(new ProducerRecord<String, String>(topic,message));
    }
    
}
