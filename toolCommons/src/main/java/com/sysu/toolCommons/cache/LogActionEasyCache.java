package com.sysu.toolCommons.cache;


import com.sysu.toolCommons.util.SysLogger;

/**
 * Created by adam on 2015/12/27.
 */
public class LogActionEasyCache<K,V> extends EasyCacheAdapter<K,V> implements SysLogger {

    public LogActionEasyCache(String name){
        super(name);
    }

    public LogActionEasyCache(String name,long period,long live){
        super(name,period,live);
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
