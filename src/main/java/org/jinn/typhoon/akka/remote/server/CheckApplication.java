package org.jinn.typhoon.akka.remote.server;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;
import akka.routing.FromConfig;
import akka.routing.RoundRobinPool;
import akka.routing.RoundRobinRouter;
import com.typesafe.config.ConfigFactory;

/**
 * Created by gumingcn on 14-7-21.
 */
public class CheckApplication implements Bootable {

    private ActorSystem system;

    public CheckApplication(String configName) {
        system = ActorSystem.create("CheckApplication", ConfigFactory.load(configName));
//        for (int i = 0; i < 500; i++) {
//            ActorRef actor = system.actorOf(Props.create(RedisCheckActor.class),
//                    "redischeckactor"+i);
//        }
        ActorRef router =system.actorOf(FromConfig.getInstance().props(Props.create(RedisCheckActor.class)), "router");
//        ActorRef router =
//                system.actorOf(new RoundRobinPool(128).props(Props.create(RedisCheckActor.class)),
//                        "router");
    }

    @Override
    public void startup() {
    }

    @Override
    public void shutdown() {
        system.shutdown();
    }

}
