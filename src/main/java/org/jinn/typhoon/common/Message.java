package org.jinn.typhoon.common;

import java.io.Serializable;

/**
 * Created by gumingcn on 14-7-17.
 */
public class Message implements Serializable {
    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public String getKey() {
        return "cart";
    }

    public void run() {
        System.out.println("process message:"+msg);
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
