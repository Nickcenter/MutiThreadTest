package 线程池.线程安全;

public class Thread1 implements Runnable {
   Object lock;
   public void run() {  
//       synchronized(lock){
           System.out.println("安全线程");
//       }
   }
}