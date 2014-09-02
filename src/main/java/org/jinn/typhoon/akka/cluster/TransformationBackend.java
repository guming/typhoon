package org.jinn.typhoon.akka.cluster;

import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;

/**
 * Created by gumingcn on 14-8-14.
 */
public class TransformationBackend extends UntypedActor{

    Cluster cluster=Cluster.get(getContext().system());

    @Override
    public void preStart() {
        cluster.subscribe(getSelf(), ClusterEvent.MemberUp.class);
    }

    @Override
    public void postStop() {
        cluster.unsubscribe(getSelf());
    }


    @Override
    public void onReceive(Object o) throws Exception {

    }
}
