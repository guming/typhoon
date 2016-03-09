package org.jinn.typhoon.akka.entity;

import akka.actor.ActorRef;
import org.jinn.typhoon.common.Message;

import java.io.Serializable;

/**
 * Created by gumingcn on 14-7-21.
 */
public class InternalMsg implements Serializable{

    public static class ActorOpMsg implements Serializable{
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
