package org.jinn.typhoon.process;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.jinn.typhoon.akka.remote.client.RemoteLookupApplication;
import org.jinn.typhoon.common.Message;
import org.jinn.typhoon.common.MessageDispatcher;

/**
 * Created by gumingcn on 14-7-17.
 */
public class MessageProcessor implements Runnable{

    private KafkaStream m_stream;

    private int threadCount;

//    AtomicInteger ai = new AtomicInteger(0);

    private MessageDispatcher la;

    public MessageProcessor(KafkaStream m_stream, int threadCount,MessageDispatcher la) {
        this.m_stream = m_stream;
        this.threadCount = threadCount;
        this.la=la;
    }


    @Override
    public void run() {

        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();

//        ActorSystem system = ActorSystem.create("MySystem");

        while (it.hasNext()) {
//            ActorRef greeter = system.actorOf(Props.create(RemoteLookupActor.class), "lookupactor"+ai.addAndGet(1));

            String msg=new String(it.next().message());
            Message message=new Message(msg);
            la.dispatchMessage(message);
//          greeter.tell(message, ActorRef.noSender());
//          DispatchJobQueue.getQueue().offer(message);
//          System.out.println(msg+","+Thread.currentThread().getId());
        }
        System.out.println("fetch none");

    }

}
