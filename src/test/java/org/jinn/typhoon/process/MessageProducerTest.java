package org.jinn.typhoon.process;

import kafka.producer.KeyedMessage;

import java.util.Date;
import java.util.Random;

/**
 * Created by gumingcn on 14-7-21.
 */
public class MessageProducerTest {

    public static void main(String[] args) {
        MessageProducer messageProducer = new MessageProducer();

        long events = 100;
        Random rnd = new Random();


        for (long nEvents = 0; nEvents < events; nEvents++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("test", ip, msg);
            messageProducer.getProducer().send(data);
        }
        messageProducer.getProducer().close();
    }

}
