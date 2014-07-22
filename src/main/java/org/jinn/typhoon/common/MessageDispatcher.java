package org.jinn.typhoon.common;

import org.jinn.typhoon.common.Message;

/**
 * Created by gumingcn on 14-7-21.
 */
public abstract class MessageDispatcher {

    public void dispatchMessage(Message message){}
    public void startup() {}
    public void shutdown() {}
}
