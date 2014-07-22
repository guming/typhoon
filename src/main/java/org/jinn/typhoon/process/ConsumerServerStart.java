package org.jinn.typhoon.process;

/**
 * Created by gumingcn on 14-7-21.
 */
public class ConsumerServerStart {

    public static void main(String[] args) {
        String zooKeeper = "localhost:2181";
        String groupId = "test_c";
        String topic = "test";
        int threads = 4;

        MessageConsumer example = new MessageConsumer(zooKeeper, groupId, topic,"akka");
        example.pull(threads);

        try {
            Thread.sleep(200*1000);
        } catch (InterruptedException ie) {

        }
//        example.shutdown();
    }
}
