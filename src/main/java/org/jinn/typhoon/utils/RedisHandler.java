package org.jinn.typhoon.utils;

import org.jinn.redis.Redis4JClient;
import org.jinn.redis.Redis4JFactory;
import org.jinn.redis.Redis4JHAServer;
import org.jinn.redis.Redis4JPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gumingcn on 14-7-23.
 */
public class RedisHandler {
    private final static Redis4JHAServer server = new Redis4JHAServer();
    public static List<Redis4JClient> clients=new ArrayList<Redis4JClient>(10);
    static {
//        for (int i = 0; i <10 ; i++) {
//            Redis4JClient r4jclient=new Redis4JClient();
//            r4jclient.setConn(new Redis4JPool(new JedisPoolConfig()));
//            r4jclient.setServer(server);
//            clients.add();
//        }
    }

    public static Redis4JHAServer getServer() {
        return server;
    }
}
