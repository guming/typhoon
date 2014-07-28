package org.jinn.typhoon.akka.remote.client;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.kernel.Bootable;
import akka.remote.routing.RemoteRouterConfig;
import akka.routing.FromConfig;
import akka.routing.RoundRobinPool;
import com.typesafe.config.ConfigFactory;
import org.jinn.typhoon.akka.remote.server.RedisCheckActor;
import org.jinn.typhoon.common.Message;
import org.jinn.typhoon.common.MessageDispatcher;

import java.util.Random;

/**
 * Created by gumingcn on 14-7-21.
 */
public class RemoteLookupApplication  extends MessageDispatcher implements Bootable {

//    Random r=new Random();
    private ActorSystem system;
    private ActorRef actor;
    private ActorRef remoteActor;

    public RemoteLookupApplication() {
        system = ActorSystem.create("RemoteLookupApplication", ConfigFactory.load("remotelookup"));
        actor = system.actorOf(Props.create(RemoteLookupActor.class), "lookupactor");
        remoteActor = system.actorOf(FromConfig.getInstance().props(Props.create(RedisCheckActor.class)),
                "aggregation");
    }

    public ActorRef getRemoteActor(){

//        remoteActor = system.actorFor(
//                "akka.tcp://CheckApplication@127.0.0.1:2552/user/router");

        return remoteActor;
    }

    @Override
    public void dispatchMessage(Message message) {

        actor.tell(new InternalMsg.ActorOpMsg(getRemoteActor(), message), null);
    }

    @Override
    public void startup() {

    }

    @Override
    public void shutdown() {
        system.shutdown();
    }
}
