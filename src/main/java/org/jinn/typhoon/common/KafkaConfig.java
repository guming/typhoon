package org.jinn.typhoon.common;

/**
 * Created by gumingcn on 14-7-28.
 */
public class KafkaConfig {

    private String hosts;
    private int port=9092;
    private String topic;
    private int partitions=1;

    private String groupId="typhoon-worker";
    private String zkHost;
    private String zkSessionTimeOut="500";//ms
    private String zkSyncTime="200";//ms
    private String autoCommitTime="1000";//ms


    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getZkHost() {
        return zkHost;
    }

    public void setZkHost(String zkHost) {
        this.zkHost = zkHost;
    }

    public String getZkSessionTimeOut() {
        return zkSessionTimeOut;
    }

    public void setZkSessionTimeOut(String zkSessionTimeOut) {
        this.zkSessionTimeOut = zkSessionTimeOut;
    }

    public String getZkSyncTime() {
        return zkSyncTime;
    }

    public void setZkSyncTime(String zkSyncTime) {
        this.zkSyncTime = zkSyncTime;
    }

    public String getAutoCommitTime() {
        return autoCommitTime;
    }

    public void setAutoCommitTime(String autoCommitTime) {
        this.autoCommitTime = autoCommitTime;
    }

    @Override
    public String toString() {
        return "KafkaConfig{" +
                "hosts='" + hosts + '\'' +
                ", port=" + port +
                ", topic='" + topic + '\'' +
                ", partitions=" + partitions +
                ", groupId='" + groupId + '\'' +
                ", zkHost='" + zkHost + '\'' +
                ", zkSessionTimeOut='" + zkSessionTimeOut + '\'' +
                ", zkSyncTime='" + zkSyncTime + '\'' +
                ", autoCommitTime='" + autoCommitTime + '\'' +
                '}';
    }
}
