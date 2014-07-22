package org.jinn.typhoon.akka.local;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.jinn.typhoon.common.Message;

/**
 * Created by gumingcn on 14-7-18.
 */
public class MessageProcessLocalActor extends UntypedActor{

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            log.info("akka actor " + ((Message) message).toString() + System.currentTimeMillis());
            getContext().stop(getSelf());
        }else{
            unhandled(message);
        }
    }

}
