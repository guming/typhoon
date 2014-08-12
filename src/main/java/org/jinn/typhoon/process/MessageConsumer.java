package org.jinn.typhoon.process;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.jinn.typhoon.akka.remote.client.RemoteLookupApplication;
import org.jinn.typhoon.common.DispatcherFactory;
import org.jinn.typhoon.common.KafkaConfig;
import org.jinn.typhoon.common.MessageDispatcher;
import org.jinn.typhoon.utils.ResourceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gumingcn on 14-7-17.
 */
public class MessageConsumer {

    private final ConsumerConnector consumer;

    private final String topic;
    private ExecutorService executor;

    private MessageDispatcher la;

    public MessageConsumer(String a_zookeeper, String a_groupId, String a_topic,String dispatcherName) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig(a_zookeeper, a_groupId));
        this.topic = a_topic;
        la= DispatcherFactory.getDispatcher(dispatcherName);
        la.startup();
    }

    public void shutdown() {
        if (consumer != null) consumer.shutdown();
        if (la != null) {
            la.shutdown();
        }
        if (executor != null) executor.shutdown();
    }

    public void pull(int a_numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(a_numThreads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        // now launch all the threads
        //
        executor = Executors.newFixedThreadPool(a_numThreads);

        // now create an object to consume the messages
        //
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.submit(new MessageProcessor(stream, threadNumber,la));
            threadNumber++;
        }
    }

    private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
        Properties props = new Properties();
        KafkaConfig kafkaConfig=ResourceUtil.getKafkaConfig();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", kafkaConfig.getZkSessionTimeOut());
        props.put("zookeeper.sync.time.ms", kafkaConfig.getZkSyncTime());
        props.put("auto.commit.interval.ms", kafkaConfig.getAutoCommitTime());

        return new ConsumerConfig(props);
    }

    public static void main(String[] args) {
        KafkaConfig kafkaConfig=ResourceUtil.getKafkaConfig();
        String zooKeeper = kafkaConfig.getZkHost();
        String groupId = kafkaConfig.getGroupId();
        String topic = kafkaConfig.getTopic();
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
