package org.jinn.typhoon.akka.remote.server;

/**
 * Created by gumingcn on 14-7-21.
 */
public class CheckServerStart {

    public static void main(String[] args) {
        CheckApplication ca=new CheckApplication("checkserver");
        ca.startup();
//        RemoteLookupApplication la = new RemoteLookupApplication();
//        la.startup();
//        la.doSomething(new Message("test:"+System.currentTimeMillis()));
    }
}
