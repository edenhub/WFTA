package com.sysu.toolCommons.cache;

/**
 * Created by adam on 2016/4/10.
 */
public abstract class CacheLifeAction<K,V> {


    /********************   Abstract Action Logic Define ********************/

    /**
     * before get cache
     * @param key
     * @return
     */
    protected  abstract boolean beforeGetCache(K key);

    /**
     * after get cache
     * @param key
     * @param value
     */
    protected  abstract void afterGetCache(K key,V value);

    /**
     * before put cache
     * @param key
     * @param value
     * @return
     */
    protected  abstract boolean beforePutCache(K key,V value);

    /**
     * after put cache
     * @param key
     * @param value
     */
    protected  abstract void afterPutCache(K key,V value);

    /**
     * before remove cache
     * @param key
     * @return
     */
    protected  abstract boolean beforeRemoveCache(K key);

    /**
     * after remove cache
     * @param key
     * @param value
     */
    protected  abstract void afterRemoveCache(K key,V value);

    /**
     * before shutdown cache
     * @return
     */
    protected  abstract boolean beforeShutDown();

    /**
     * after shutdown cache
     */
    protected  abstract void afterShutDown();

    /********************   Action Interface Define ********************/
}
