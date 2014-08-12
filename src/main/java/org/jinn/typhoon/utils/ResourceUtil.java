package org.jinn.typhoon.utils;

import org.apache.commons.lang.StringUtils;
import org.jinn.typhoon.common.KafkaConfig;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.Properties;

/**
 * Created by gumingcn on 14-7-28.
 */
public class ResourceUtil {

    private static String FIX=":";

    public static JedisPoolConfig loadRedisPoolConfig() {
        Properties properties;
        try {
            properties = ResourceUtil.getResourceAsProperties("typhoon.properties",
                    "UTF-8");
            final JedisPoolConfig redisPoolConfig = new JedisPoolConfig();
            if (StringUtils.isNotBlank(properties.getProperty("redis.pool.maxActive"))) {
                redisPoolConfig.setMaxActive(Integer.valueOf(properties.getProperty("redis.pool.maxActive")));
            }

            if (StringUtils.isNotBlank(properties
                    .getProperty("redis.pool.maxIdle"))) {
                redisPoolConfig.setMaxIdle(Integer.parseInt(properties
                        .getProperty("redis.pool.maxIdle")));
            }

            if (StringUtils.isNotBlank(properties
                    .getProperty("redis.pool.testOnBorrow"))) {
                redisPoolConfig.setTestOnBorrow(Boolean.valueOf(properties
                        .getProperty("redis.pool.testOnBorrow")));
            }

            if (StringUtils.isNotBlank(properties
                    .getProperty("redis.pool.testWhileIdel"))) {
                redisPoolConfig.setTestWhileIdle(Boolean.valueOf(properties
                        .getProperty("redis.pool.testWhileIdel")));
            }

            return redisPoolConfig;
        } catch (final IOException e) {
            return null;
        }
    }

    public static String getRedisHosts() {
        Properties properties;
        try {
            properties = ResourceUtil.getResourceAsProperties("typhoon.properties",
                    "UTF-8");
            String env=properties.getProperty("env.value");

          if (StringUtils.isNotBlank(env)&&env.equals("local")&&StringUtils.isNotBlank(properties.getProperty("redis.hosts"))) {
            return properties.getProperty("redis.hosts");
          }
            else {
              return getHosts();
          }
        }catch (final IOException e) {
            return "";
        }

    }
    private static String getHosts(){
        StringBuffer sb=new StringBuffer();
        sb.append(System.getenv("VIPCART_REDIS_A_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_A_PORT"))
        .append(",").append(System.getenv("VIPCART_REDIS_B_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_B_PORT"))
                .append(",").append(System.getenv("VIPCART_REDIS_C_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_C_PORT"))
                .append(",").append(System.getenv("VIPCART_REDIS_D_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_D_PORT"))
                .append(",").append(System.getenv("VIPCART_REDIS_E_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_E_PORT"))
                .append(",").append(System.getenv("VIPCART_REDIS_F_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_F_PORT"))
                .append(",").append(System.getenv("VIPCART_REDIS_G_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_G_PORT"))
                .append(",").append(System.getenv("VIPCART_REDIS_H_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_H_PORT"))
                .append(",").append(System.getenv("VIPCART_REDIS_I_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_I_PORT"))
                .append(",").append(System.getenv("VIPCART_REDIS_J_HOST")).append(FIX).append(System.getenv("VIPCART_REDIS_J_PORT"));
        return sb.toString();
    }
    public static KafkaConfig getKafkaConfig() {

        final KafkaConfig kconfig=new KafkaConfig();
        Properties properties;
        try {
            properties = ResourceUtil.getResourceAsProperties("typhoon.properties",
                    "UTF-8");
            String env=properties.getProperty("env.value");
            if (StringUtils.isNotBlank(properties.getProperty("kafka.hosts"))) {
                kconfig.setHosts(properties.getProperty("kafka.hosts"));
            }
            if (env != null && env.equals("local")) {

                if (StringUtils.isNotBlank(properties.getProperty("zookeeper.connect"))) {
                    kconfig.setZkHost(properties.getProperty("zookeeper.connect"));
                }
            }else {
                //urgly
                if (StringUtils.isNotBlank(System.getenv("VIPCART_KAFKA_ZOOKEEPER_HOST"))) {
                    kconfig.setZkHost(System.getenv("VIPCART_KAFKA_ZOOKEEPER_HOST")+":"+System.getenv("VIPCART_KAFKA_ZOOKEEPER_PORT"));
                }

            }
            if (StringUtils.isNotBlank(properties.getProperty("kafka.topics"))) {
                kconfig.setTopic(properties.getProperty("kafka.topics"));
            }
            if (StringUtils.isNotBlank(properties.getProperty("kafka.partitions.num"))) {
                kconfig.setPartitions(Integer.valueOf(properties.getProperty("kafka.partitions.num")));
            }
            if (StringUtils.isNotBlank(properties.getProperty("kafka.group.id"))) {
                kconfig.setGroupId(properties.getProperty("kafka.group.id"));
            }


            if (StringUtils.isNotBlank(properties.getProperty("zookeeper.session.timeout.ms"))) {
                kconfig.setZkSessionTimeOut(properties.getProperty("zookeeper.session.timeout.ms"));
            }
            if (StringUtils.isNotBlank(properties.getProperty("zookeeper.sync.time.ms"))) {
                kconfig.setZkSyncTime(properties.getProperty("zookeeper.sync.time.ms"));
            }
            if (StringUtils.isNotBlank(properties.getProperty("kafka.auto.commit.interval.ms"))) {
                kconfig.setAutoCommitTime(properties.getProperty("kafka.auto.commit.interval.ms"));
            }

        }catch (final IOException e) {
            return null;
        }
        return kconfig;

    }

    public static Properties getResourceAsProperties(String resource,
                                                     String encoding) throws IOException {
        InputStream in = null;
        try {
            in = getResourceAsStream(resource);
        } catch (IOException e) {
            File file = new File(resource);
            if (!file.exists()) {
                throw e;
            }
            in = new FileInputStream(file);
        }

        Reader reader = new InputStreamReader(in, encoding);
        Properties props = new Properties();
        props.load(reader);
        in.close();
        reader.close();

        return props;

    }

    public static InputStream getResourceAsStream(String resource)
            throws IOException {
        InputStream in = null;
        ClassLoader loader = ResourceUtil.class.getClassLoader();
        if (loader != null) {
            in = loader.getResourceAsStream(resource);
        }
        if (in == null) {
            in = ClassLoader.getSystemResourceAsStream(resource);
        }
        if (in == null) {
            throw new IOException("Could not find resource " + resource);
        }
        return in;
    }

    public static void main(String[] args) {
        System.out.println(ResourceUtil.getRedisHosts());
        System.out.println(ResourceUtil.getKafkaConfig().toString());
    }

}
