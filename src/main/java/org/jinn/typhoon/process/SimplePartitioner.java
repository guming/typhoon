package org.jinn.typhoon.process;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by gumingcn on 14-7-17.
 */

public class SimplePartitioner implements Partitioner {

    public SimplePartitioner(VerifiableProperties props) {

    }

    public int partition(Object key, int numPartitions) {
        String key_str = key.toString();
        int partition = 0;
        int offset = key_str.lastIndexOf('.');
        if (offset > 0) {
            partition = Integer.parseInt(key_str.substring(offset + 1)) % numPartitions;
        }
        return partition;
    }
}
