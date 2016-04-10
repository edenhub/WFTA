package com.sysu.toolCommons.cache;

import com.sysu.toolCommons.util.SysLogger;

/**
 * Created by adam on 2016/4/10.
 */
public class LogActionTwoQueuesCache<K,V> extends TwoQueuesCacheAdapter<K,V> implements SysLogger {

    public LogActionTwoQueuesCache(String name, int maxSize) {
        super(name, maxSize);
    }

    public LogActionTwoQueuesCache(String name, int maxSize, long period, long live) {
        super(name, maxSize, period, live);
    }

    @Override
    protected boolean beforeGetCache(K key) {
        if (logger.isInfoEnabled())
            logger.info("before get cache,key : "+key);

        return true;
    }

    @Override
    protected void afterGetCache(K key, V value) {
        if (logger.isInfoEnabled())
            logger.info("after get cache,key : "+key);
    }

    @Override
    protected boolean beforePutCache(K key, V value) {
        if (logger.isInfoEnabled())
            logger.info("before put cache,key : "+key);

        return true;
    }

    @Override
    protected void afterPutCache(K key, V value) {
        if (logger.isInfoEnabled())
            logger.info("after put cache,key : "+key);
    }

    @Override
    protected boolean beforeRemoveCache(K key) {
        if (logger.isInfoEnabled())
            logger.info("before remove cache,key : "+key);

        return true;
    }

    @Override
    protected void afterRemoveCache(K key, V value) {
        if (logger.isInfoEnabled())
            logger.info("after remove cache,key : "+key);
    }

    @Override
    protected boolean beforeShutDown() {
        if (logger.isInfoEnabled())
            logger.info("before shutdown caches");

        return true;
    }

    @Override
    protected void afterShutDown() {
        if (logger.isInfoEnabled())
            logger.info("after shutdown caches");
    }
}
