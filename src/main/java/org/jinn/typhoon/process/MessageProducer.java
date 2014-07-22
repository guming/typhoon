package org.jinn.typhoon.process;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by gumingcn on 14-7-17.
 */
public class MessageProducer {
    private static ProducerConfig config=null;
    static Producer<String, String> producer;

    public MessageProducer() {
        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "org.jinn.typhoon.process.SimplePartitioner");
        props.put("request.required.acks", "1");
        config = new ProducerConfig(props);
        producer= new Producer<String, String>(config);
    }

    public static ProducerConfig getConfig() {
        return config;
    }

    public Producer<String, String> getProducer() {
        return producer;
    }

}
