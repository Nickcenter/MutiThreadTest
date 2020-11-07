package 线程池.线程安全;

/**
 * Created by nick on 2020/11/7.
 */
public class 线程安全 {
    public static void main(String[] args) throws InterruptedException {
        Thread1 wT1= new Thread1();
        Thread wT2=new Thread(wT1);
        wT2.run();
    }
}
