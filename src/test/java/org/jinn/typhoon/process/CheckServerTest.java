package org.jinn.typhoon.process;

import org.jinn.typhoon.akka.remote.server.CheckApplication;

/**
 * Created by gumingcn on 14-7-21.
 */
public class CheckServerTest {

    public static void main(String[] args) {
        CheckApplication ca=new CheckApplication("checkserver2");
        ca.startup();
//        RemoteLookupApplication la = new RemoteLookupApplication();
//        la.startup();
//        la.doSomething(new Message("test:"+System.currentTimeMillis()));
    }

}
