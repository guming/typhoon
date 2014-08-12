package org.jinn.typhoon.akka.remote.server;

import org.jinn.typhoon.akka.remote.client.RemoteLookupApplication;
import org.jinn.typhoon.common.Message;

/**
 * Created by gumingcn on 14-7-21.
 */
public class CheckWorkerStart {

    public static void main(String[] args) {
        CheckApplication ca=new CheckApplication("checkserver");
        ca.startup();
//        RemoteLookupApplication la = new RemoteLookupApplication();
//        la.startup();
//        for (int i = 0; i <1000; i++) {
//            la.dispatchMessage(new Message("test:"+System.currentTimeMillis()));
//        }
        System.out.println(System.currentTimeMillis()+",end");
    }
}
