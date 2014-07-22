package org.jinn.typhoon.common;

import org.jinn.typhoon.akka.remote.client.RemoteLookupApplication;

/**
 * Created by gumingcn on 14-7-21.
 */
public class DispatcherFactory {
    public static final String STORM="storm";

    public static final String AKKA="akka";

    public static MessageDispatcher getDispatcher(String name){

        if (name.equalsIgnoreCase("storm")) {
            return null;
        }else {
            return new RemoteLookupApplication();
        }

    }

}
