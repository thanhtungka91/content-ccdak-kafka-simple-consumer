package com.linuxacademy.ccdak.kafkaSimpleConsumer;

import org.apache.kafka.clients.consumer.*;
import java.util.Properties;
import java.util.Arrays;
import java.time.Duration;


public class Main {

    public static void main(String[] args) {
        Properties props = new Properties(); // create a new Properotoes
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "my-group");
        props.setProperty("enable.auto.commit", "true"); //add commit- required
        props.setProperty("auto.commit.interval.ms", "1000"); // intervew .ms 1s
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("inventory_purchases"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

}