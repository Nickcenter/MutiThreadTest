package 线程池.不同类型的线程;

import volitale关键字.Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nick on 2018/10/3.
 */
public class CachedThreadPoolExample {
    public static void main(String[] args) {

        ExecutorService exectorService= Executors.newCachedThreadPool();
        for(int i =0;i<10;i++){
            final  int index = i;
            exectorService.execute(new Runnable() {
                public void run() {
                    Singleton m=Singleton.getInstance();
                    System.out.println(m.hashCode());
                    System.out.println("index="+index);
                }
            });
        }
    }
}
