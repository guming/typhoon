package org.jinn.typhoon.akka.remote.client;

import akka.actor.ActorRef;
import org.jinn.typhoon.common.Message;

/**
 * Created by gumingcn on 14-7-21.
 */
public class InternalMsg {

    public static class ActorOpMsg {
        private final ActorRef actor;
        private final Message msg;

        public ActorOpMsg(ActorRef actor, Message msg) {
            this.actor = actor;
            this.msg = msg;
        }

        public ActorRef getActor() {
            return actor;
        }

        public Message getMsg() {
            return msg;
        }
    }

}
