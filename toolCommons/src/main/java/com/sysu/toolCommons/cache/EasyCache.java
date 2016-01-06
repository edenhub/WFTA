package com.sysu.toolCommons.cache;

import com.sysu.toolCommons.util.SysLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by adam on 2015/12/27.
 */
public abstract class EasyCache<K,V> {

    /********************   Abstract Action Logic Define ********************/

    /**
     * before get cache
     * @param key
     * @return
     */
    protected abstract boolean beforeGetCache(K key);

    /**
     * after get cache
     * @param key
     * @param value
     */
    protected abstract void afterGetCache(K key,V value);

    /**
     * before put cache
     * @param key
     * @param value
     * @return
     */
    protected abstract boolean beforePutCache(K key,V value);

    /**
     * after put cache
     * @param key
     * @param value
     */
    protected abstract void afterPutCache(K key,V value);

    /**
     * before remove cache
     * @param key
     * @return
     */
    protected abstract boolean beforeRemoveCache(K key);

    /**
     * after remove cache
     * @param key
     * @param value
     */
    protected abstract void afterRemoveCache(K key,V value);

    /**
     * before shutdown cache
     * @return
     */
    protected abstract boolean beforeShutDown();

    /**
     * after shutdown cache
     */
    protected abstract void afterShutDown();

    /********************   Action Interface Define ********************/


    /********************   DemoTimerTask Implement ********************/

    /**
     * the task to clean the cache which is timeout
     */
    private class DemoTimerTask extends TimerTask implements SysLogger {

        @Override
        public void run() {
            if (logger.isInfoEnabled()){
                logger.info("DemoTimer : "+ getDemoName() +" start execute");
            }

            //TODO:测试
            lock.lock();
            try{
                if (cacheTag.size() == 0) {
                    if (logger.isInfoEnabled()){
                        logger.info("DemoTimer : "+ getDemoName() +" end execute for empty cache");
                    }
                    return;
                }
                long currentTime = System.currentTimeMillis();
                for(K k : cacheTag.keySet()){
                    Long preTime = cacheTag.get(k);
                    if (preTime == null) continue;
                    long step = currentTime - preTime;
                    if (step > live){
                        removeCache(k);
                    }
                }
            }finally {
                lock.unlock();
            }

            if (logger.isInfoEnabled()){
                logger.info("DemoTimer : "+ getDemoName() +" end execute for remove action");
            }
        }
    }

    /********************   DemoTimerTask Implement ********************/
    private Lock lock;

    //time to clean the cache in duration
    private long period;

    //container to keep the cache object
    private Map<K,V> cacheCntner;

    //container to keep the cache's time
    private Map<K,Long> cacheTag;

    private Timer demoRunner;

    private String demoName;

    //time the cache can save
    private long live;

    private boolean isShutDown = false;

    //默认5分钟清理缓存
    public EasyCache(String name){
        lock = new ReentrantLock();
        period = 1000 * 60 * 5;
        live = 1000 * 60 * 5;

        cacheCntner = new HashMap<K, V>();
        cacheTag = new HashMap<K, Long>();

        this.demoName = name;

        demoRunner = new Timer(demoName);

        startDemoRunner();
    }

    public EasyCache(String name,long period,long live){
        this(name);
        this.period = period;
        this.live = live;
        startDemoRunner();
    }

    //2分钟后开始启动
    protected void startDemoRunner(){
        demoRunner.schedule(new DemoTimerTask(),1000 * 60 * 2,period);
    }

    /********************   Main Function ********************/

    public void shutDown(){
        lock.lock();
        if (isShutDown) return;
        demoRunner.cancel();
        try{
            boolean ret = beforeShutDown();
            if (!ret) return;
            cacheCntner.clear();
            cacheTag.clear();
            isShutDown = true;
            afterShutDown();
        }finally {
            lock.unlock();
        }
    }

    public V getCache(K key){
        V value = null;
        boolean ret = beforeGetCache(key);
        if (!ret) return value;

        value = cacheCntner.get(key);
        afterGetCache(key,value);
        return value;
    }

    public void putCache(K key,V value){
        lock.lock();

        try{
            boolean ret = beforePutCache(key,value);
            if (!ret) return;
            cacheCntner.put(key,value);
            cacheTag.put(key,System.currentTimeMillis());
            afterPutCache(key, value);
        }finally {
            lock.unlock();
        }
    }

    public V removeCache(K key){
        lock.lock();
        V value = null;
        try{
            boolean ret = beforeRemoveCache(key);
            if (!ret) return value;
            value = cacheCntner.remove(key);
            cacheTag.remove(key);
            afterRemoveCache(key,value);
        }finally {
            lock.unlock();
        }

        return value;
    }

    public boolean isShutDown() {
        return isShutDown;
    }

    /********************   Main Function ********************/

    public long getLive() {
        return live;
    }

    public void setLive(long live) {
        this.live = live;
    }

    public void setShutDown(boolean isShutDown) {
        this.isShutDown = isShutDown;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }
}
