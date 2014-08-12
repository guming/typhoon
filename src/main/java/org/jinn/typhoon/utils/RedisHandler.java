package org.jinn.typhoon.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gumingcn on 14-7-23.
 */
public class RedisHandler {
    public static  JedisPoolConfig config;
    public static List<JedisPool> clients=new ArrayList<JedisPool>(10);

    static {
        config = ResourceUtil.loadRedisPoolConfig();
        String hosts_str=ResourceUtil.getRedisHosts();
        String[] hosts=hosts_str.split(",");
        for (String host : hosts) {
            String h="127.0.0.1";
            int port=6379;
            if (host != null) {
                String [] temp=host.split(":");
                port=Integer.valueOf(temp[1]);
                h=temp[0];
            }
            JedisPool pool=new JedisPool(config,h,port);
            clients.add(pool);
        }
    }
    public static JedisPool getClientPool(int index){
       return clients.get(index);
    }

    public static String hget(final int index,String redis_key,String filed){
//        System.out.println(index+","+"redis_key:"+redis_key+",filed:"+filed);
        JedisPool pool=clients.get(index);
        Jedis jedis = pool.getResource();
        String value=jedis.hget(redis_key,filed);
        pool.returnResource(jedis);
        return value;
    }

    public static void main(String[] args) {
        System.out.println( RedisHandler.hget(1, "vipcart_7_VH_NH", "update_time"));
    }
}
