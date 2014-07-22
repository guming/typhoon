package org.jinn.typhoon.akka.remote.server;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;
import com.typesafe.config.ConfigFactory;

/**
 * Created by gumingcn on 14-7-21.
 */
public class CheckApplication implements Bootable {

    private ActorSystem system;

    public CheckApplication(String configName) {
        system = ActorSystem.create("CheckApplication", ConfigFactory.load(configName));
        for (int i = 0; i < 500; i++) {
            ActorRef actor = system.actorOf(Props.create(RedisCheckActor.class),
                    "redischeckactor"+i);
        }
    }

    @Override
    public void startup() {
    }

    @Override
    public void shutdown() {
        system.shutdown();
    }

}
