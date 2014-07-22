package org.jinn.typhoon.akka.remote.server;

import akka.actor.UntypedActor;
import org.jinn.typhoon.common.Message;

/**
 * Created by gumingcn on 14-7-21.
 */
public class RedisCheckActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Message) {
            System.out.println(message.toString() + "," + System.currentTimeMillis());
            Thread.sleep(10);
        }
        else
            unhandled(message);
    }

}
