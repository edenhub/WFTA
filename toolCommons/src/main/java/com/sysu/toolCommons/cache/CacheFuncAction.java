package com.sysu.toolCommons.cache;

/**
 * Created by adam on 2016/4/10.
 */
public interface CacheFuncAction<K,V> {

    public V getCache(K key);

    public void putCache(K key,V value);

    public V removeCache(K key);

    public void shutDown();

    public boolean isShutDown();
}
