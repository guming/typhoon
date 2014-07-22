package org.jinn.typhoon.akka.remote.client;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.kernel.Bootable;
import com.typesafe.config.ConfigFactory;
import org.jinn.typhoon.common.Message;
import org.jinn.typhoon.common.MessageDispatcher;

import java.util.Random;

/**
 * Created by gumingcn on 14-7-21.
 */
public class RemoteLookupApplication  extends MessageDispatcher implements Bootable {

    Random r=new Random();
    private ActorSystem system;
    private ActorRef actor;
    private ActorRef remoteActor;

    public RemoteLookupApplication() {
        system = ActorSystem.create("RemoteLookupApplication", ConfigFactory.load("remotelookup"));
        actor = system.actorOf(Props.create(RemoteLookupActor.class), "lookupactor");
    }

    public ActorRef getRemoteActor(int number){
        remoteActor = system.actorFor(
                "akka.tcp://CheckApplication@127.0.0.1:2552/user/redischeckactor"+number);
        return remoteActor;
    }

    @Override
    public void dispatchMessage(Message message) {

        actor.tell(new InternalMsg.ActorOpMsg(getRemoteActor(r.nextInt(1000)), message), null);
    }

    @Override
    public void startup() {

    }

    @Override
    public void shutdown() {
        system.shutdown();
    }
}
