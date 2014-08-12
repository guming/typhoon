package org.jinn.typhoon.process;

import org.jinn.typhoon.common.KafkaConfig;
import org.jinn.typhoon.utils.ResourceUtil;

/**
 * Created by gumingcn on 14-7-21.
 */
public class ConsumerServerStart {
    static MessageConsumer example;
    public static void main(String[] args) {
//        String action=System.getProperty("action");
//        if (action == null) {
//            return;
//        }
        String action="start";
        if(action.equals("start")) {
            KafkaConfig kafkaConfig = ResourceUtil.getKafkaConfig();
            String zooKeeper = kafkaConfig.getZkHost();
            String groupId = kafkaConfig.getGroupId();
            String topic = kafkaConfig.getTopic();
            System.out.println(zooKeeper + "," + groupId + "," + topic);
            int threads = 4;
            example = new MessageConsumer(zooKeeper, groupId, topic, "akka");
            example.pull(threads);
        }else {
            example.shutdown();
        }
    }
}
