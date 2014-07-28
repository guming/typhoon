package org.jinn.typhoon.akka.remote.server;

import akka.actor.UntypedActor;
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
        if(message instanceof Message) {

            String msg=((Message) message).getMsg();
            CartMessage cm= (CartMessage) JSONUtil.jsonToBean(msg, CartMessage.class);
            StringBuffer redis_key=new StringBuffer(32);
            redis_key.append(Constant.VIPCART).append(cm.getInfo().getUser_id())
                    .append(Constant.UNDERLINE)
                    .append(cm.getInfo().getWarehouse());
            String redis_value=RedisHandler.getServer().hget(redis_key.toString(),
                    Constant.UPDATE_TIME);
            Double redis_updateTime=Double.valueOf(redis_value);
            Double updateTime=Double.valueOf(cm.getTime());
            if(updateTime>redis_updateTime){
               CommonLogger.fire("check fired redis_key:"+redis_key+"redis_ut:"+redis_value+",kafka_ut:"+cm.getTime()+"msg:"+cm.toString());
            }

//            Thread.sleep(10);
//            System.out.println("end "+message.toString() + "," + System.currentTimeMillis());
        }
        else
            unhandled(message);
    }

}
