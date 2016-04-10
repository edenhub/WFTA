package TestTwoQueuesCache;

import com.sysu.toolCommons.cache.CacheFuncAction;
import com.sysu.toolCommons.cache.LogActionTwoQueuesCache;
import com.sysu.toolCommons.cache.TwoQueuesCache;
import org.junit.Test;

/**
 * Created by adam on 2016/4/10.
 */
public class TwoQueuesCacheTest {

//    @Test
    public void test2Q() throws InterruptedException {
        String name = "TwoQ";
        int maxSize = 100;

        CacheFuncAction<String,String> cache2Q = new LogActionTwoQueuesCache<String, String>(name,maxSize,1000 * 10,1000 * 10);

        cache2Q.putCache("a","a");
        cache2Q.putCache("b", "b");
        cache2Q.putCache("c", "c");

        String v = cache2Q.getCache("a");
        assert v.equals("a");
        System.out.println("assert : true");

        Thread.sleep(1000 * 60 * 10);
        cache2Q.removeCache("b");
        cache2Q.shutDown();
        v = cache2Q.getCache("a");
        assert v == null;
    }

    public static void testThread(){
        String name = "TwoQ";
        int maxSie = 1000;

        final CacheFuncAction<String,String> cache2Q = new LogActionTwoQueuesCache<String, String>(name,maxSie);

        Thread[] threads = new Thread[6];
        for (int i=0;i<3;i++){
            final int finalI = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<100;j++)
                        cache2Q.putCache("a_"+ finalI+"_"+j,"a"+finalI+"_"+j);
                }
            });
        }

        for(int i=0;i<3;i++){
            final int finalI = i;
            threads[i+3] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<100;j++){
                        cache2Q.removeCache("a_"+finalI+"_"+j);
                    }
                }
            });
        }

        for(int i=0;i<6;i++){
            threads[i].start();
        }

        cache2Q.shutDown();
        System.out.println("end ..");

    }

    public static void main(String[] args) throws InterruptedException {
        testThread();
//        new TwoQueuesCacheTest().test2Q();
    }
}
