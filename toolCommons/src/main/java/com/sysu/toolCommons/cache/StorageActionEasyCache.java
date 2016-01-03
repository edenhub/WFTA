package com.sysu.toolCommons.cache;

/**
 * 用于缓存落地
 * Created by adam on 2015/12/27.
 */
public class StorageActionEasyCache<K,V> extends LogActionEasyCache<K,V> {
    public StorageActionEasyCache(String name) {
        super(name);
    }

    public StorageActionEasyCache(String name, long period, long live) {
        super(name, period, live);
    }
}
