package com.sysu.toolCommons.cache;

/**
 * Created by adam on 2016/4/10.
 */
public class TwoQueuesCacheAdapter<K,V> extends TwoQueuesCache<K,V> {
    public TwoQueuesCacheAdapter(String name, int maxSize) {
        super(name, maxSize);
    }

    public TwoQueuesCacheAdapter(String name, int maxSize, long period, long live) {
        super(name, maxSize, period, live);
    }

    @Override
    protected boolean beforeGetCache(K key) {
        return false;
    }

    @Override
    protected void afterGetCache(K key, V value) {

    }

    @Override
    protected boolean beforePutCache(K key, V value) {
        return false;
    }

    @Override
    protected void afterPutCache(K key, V value) {

    }

    @Override
    protected boolean beforeRemoveCache(K key) {
        return false;
    }

    @Override
    protected void afterRemoveCache(K key, V value) {

    }

    @Override
    protected boolean beforeShutDown() {
        return false;
    }

    @Override
    protected void afterShutDown() {

    }
}
