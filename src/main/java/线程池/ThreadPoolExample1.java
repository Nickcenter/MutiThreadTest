package 线程池;

import volitale.Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nick on 2018/10/3.
 */
public class ThreadPoolExample1 {
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
