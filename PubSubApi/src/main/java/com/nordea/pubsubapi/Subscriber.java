/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordea.pubsubapi;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 *
 * @author Tore
 */
public class Subscriber extends Connector {

    public ArrayList<String> subscribe(String topic) {
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
