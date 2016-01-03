package com.sysu.toolCommons.cache;

/**
 * Created by adam on 2015/12/27.
 */
public class EasyCacheAdapter<K,V> extends EasyCache<K,V> {

    public EasyCacheAdapter(String name){
        super(name);
    }

    public EasyCacheAdapter(String name,long period,long live){
        super(name,period,live);
    }

    @Override
    protected boolean beforeGetCache(K key) {
        return true;
    }

    @Override
    protected void afterGetCache(K key, V value) {

    }

    @Override
    protected boolean beforePutCache(K key, V value) {
        return true;
    }

    @Override
    protected void afterPutCache(K key, V value) {

    }

    @Override
    protected boolean beforeRemoveCache(K key) {
        return true;
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
