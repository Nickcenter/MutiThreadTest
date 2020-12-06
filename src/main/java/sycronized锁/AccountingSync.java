package sycronized锁;

/**
 * Created by nick on 2018/10/4.
 */
public class AccountingSync implements Runnable{
    //共享资源(临界资源)
    static int i=0;

    /**
     * synchronized 修饰实例方法
     *
     *
修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁（一个对象，只有一把锁）

修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁（两个对象）

修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁（在某些情况下，我们编写的方法体可能比较大，同时存在一些比较耗时的操作，而需要同步的代码又只有一小部分）

* synchronized方法底层原理

     方法级的同步是隐式，即无需通过字节码指令来控制的，它实现在方法调用和返回操作之中。
     JVM可以从方法常量池中的方法表结构(method_info Structure) 中的 ACC_SYNCHRONIZED 访问标志区分一个方法是否同步方法。
     当方法调用时，调用指令将会 检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，
     执行线程将先持有monitor（虚拟机规范中用的是管程一词）， 然后再执行方法，最后再方法完成(无论是正常完成还是非正常完成)时释放monitor。
     在方法执行期间，执行线程持有了monitor，其他任何线程都无法再获得同一个monitor。如果一个同步方法执行期间抛 出了异常，
     并且在方法内部无法处理此异常，那这个同步方法所持有的monitor将在异常抛到同步方法之外时自动释放。下面我们看看字节码层面如何实现：

synchronized的锁分类：
     偏向锁：偏向锁的核心思想是，如果一个线程获得了锁，那么锁就进入偏向模式，此时Mark Word 的结构也变为偏向锁结构，
     当这个线程再次请求锁时，无需再做任何同步操作，即获取锁的过程，这样就省去了大量有关锁申请的操作，从而也就提供程序的性能。
     轻量级锁：
     自旋锁：轻量级锁失败后，虚拟机为了避免线程真实地在操作系统层面挂起，还会进行一项称为自旋锁的优化手段。
     锁消除：通过对运行上下文的扫描，去除不可能存在共享资源竞争的锁，通过这种方式消除没有必要的锁

重入锁：
     synchronized的可重入性
     从互斥锁的设计上来说，当一个线程试图操作一个由其他线程持有的对象锁的临界资源时，
     将会处于阻塞状态，但当一个线程再次请求自己持有对象锁的临界资源时，这种情况属于重入锁，请求将会成功，

Lock是可中断锁，sycronized是不可中断锁

Lock是一个关键字，syncronized是一个关键字

sycronized在发生异常的时候，会自动释放线程占有的锁，因此不会导致死锁；lock不行

Lock可以通过tryLock（）知道有没有成功获得锁，sycronized则不行

Lock可以实现公平锁，sycronized不保证公平锁（公平锁：尽量已请求锁的顺序获得锁）
     */


    public synchronized void increase(){
        i++;
    }
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        AccountingSync instance=new AccountingSync();
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
    /**
     * 输出结果:
     * 2000000
     */
}
