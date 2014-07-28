package org.jinn.typhoon.process;

import kafka.producer.KeyedMessage;
import org.jinn.typhoon.utils.JSONUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("action","add");
            map.put("time", "1406168332.35081900");
            map.put("redis_key_hash", "1");
            map.put("hash", nEvents);
            String json=null;
            try {
                json=JSONUtil.beanToJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("test", json+nEvents, json);
            messageProducer.getProducer().send(data);
        }
        KeyedMessage<String, String> data = new KeyedMessage<String, String>("test", "0.0.0.0", "1");
        messageProducer.getProducer().send(data);

        messageProducer.getProducer().close();
    }

}
