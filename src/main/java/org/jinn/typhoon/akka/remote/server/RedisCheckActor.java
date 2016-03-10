package org.jinn.typhoon.akka.remote.server;

import akka.actor.UntypedActor;
import org.apache.commons.lang.StringUtils;
import org.jboss.netty.util.internal.StringUtil;
import org.jinn.typhoon.akka.entity.InternalMsg;
import org.jinn.typhoon.common.CartMessage;
import org.jinn.typhoon.common.Message;
import org.jinn.typhoon.utils.CommonLogger;
import org.jinn.typhoon.utils.Constant;
import org.jinn.typhoon.utils.JSONUtil;
import org.jinn.typhoon.utils.RedisHandler;

/**
 * Created by gumingcn on 14-7-21.
 */
public class RedisCheckActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof InternalMsg.ActorOpMsg) {

            Message msg=((InternalMsg.ActorOpMsg) message).getMsg();
            CartMessage cm= (CartMessage) JSONUtil.jsonToBean(msg.getMsg(), CartMessage.class);
            System.out.println("kafka not found the updatetime or value is blank,key:" + cm.toString());
            CommonLogger.fire("not found:"+cm.toString());
//            StringBuffer redis_key = new StringBuffer(32);
//            redis_key.append(Constant.VIPCART).append(cm.getInfo().getUser_id())
//                    .append(Constant.UNDERLINE)
//                    .append(cm.getInfo().getWarehouse());
//            if(StringUtils.isBlank(cm.getRedis_key_hash())){//redishash is null or empty
//                return;
//            }
//            String redis_value = RedisHandler.hget(Integer.valueOf(cm.getRedis_key_hash()), redis_key.toString(),
//                    Constant.UPDATE_TIME);
//
//            if(!cm.getAction().equalsIgnoreCase("delete")) {
//                if (StringUtils.isBlank(redis_value)) {
//                    CommonLogger.fire("redis not found the key or value is blank,key:" + redis_key.toString());
//                } else {
//                    Double redis_updateTime = Double.valueOf(redis_value);
//                    String kafka_time = cm.getTime();
//                    if (StringUtils.isNotBlank(kafka_time)) {
//                        Double updateTime = Double.valueOf(kafka_time);
//                        if (updateTime > redis_updateTime) {
//                            CommonLogger.fire("check fired redis_key:" + redis_key + "redis_ut:" + redis_value + ",kafka_ut:" + cm.getTime() + "msg:" + cm.toString());
//                        } else {
////                        System.out.println(redis_value+","+redis_key);
//                        }
//                    } else {
//                        CommonLogger.fire("kafka not found the updatetime or value is blank,key:" + cm.toString());
//                    }
//                }
//            }else{
//                //delete cart
//                if (redis_value != null) {
//                    CommonLogger.fire("check fired redis_key:" + redis_key + "redis_ut:" + redis_value + ",kafka_ut:" + cm.getTime() + "msg:" + cm.toString());
//                }
//            }
        }
        else
            unhandled(message);
    }

}
