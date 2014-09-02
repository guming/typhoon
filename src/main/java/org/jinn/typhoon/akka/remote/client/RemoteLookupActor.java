package org.jinn.typhoon.akka.remote.client;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.jinn.typhoon.akka.entity.InternalMsg;

/**
 * Created by gumingcn on 14-7-21.
 */
public class RemoteLookupActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        log.info(((InternalMsg.ActorOpMsg)message).getMsg().toString());
        if (message instanceof InternalMsg.ActorOpMsg) {

            // send message to server actor
            InternalMsg.ActorOpMsg msg = (InternalMsg.ActorOpMsg) message;
            msg.getActor().tell(msg.getMsg(), getSelf());

        }  else {
            unhandled(message);
        }
    }

}