package org.jinn.typhoon.process;

import kafka.producer.KeyedMessage;
import org.jinn.typhoon.common.Info;
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

        long events = 10000;

        for (long nEvents = 0; nEvents < events; nEvents++) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("action","add");
            map.put("time", "1406168332.35081900");
            map.put("redis_key_hash", "1");
            Info info = new Info();
            info.setCart_id(1l);
            info.setWarehouse("VH_NH");
            info.setCart_record_id(1l);
            info.setChannel("1001");
            info.setUser_id(nEvents);
            map.put("info",info);

            String temp="{\"action\":\"edit\",\"redis_key_hash\":\"1\",\"DB_key_hash\":\"\"," +
                    "\"time\":\"1406168332.35081900\",\"source\":\"web\",\"mars_cid\":\"\"," +
                    "\"session_id\":\"\",\"info\":{\"cart_id\":\"6185\",\"user_id\":\""+nEvents+"\",\"brand_id\":\"7511\"," +
                    "\"num\":2,\"warehouse\":\"VIP_NH\",\"merchandise_id\":\"1001950\",\"channel\":\"te\"," +
                    "\"cart_record_id\":\"8765\",\"size_id\":\"2756943\"}}";

//            map.put("hash", nEvents);

            String json=null;
            try {
                json=JSONUtil.beanToJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("cart_operation_data", "json", temp);
            messageProducer.getProducer().send(data);
        }
//        KeyedMessage<String, String> data = new KeyedMessage<String, String>("test", "0.0.0.0", "1");
//        messageProducer.getProducer().send(data);

        messageProducer.getProducer().close();
    }

}
