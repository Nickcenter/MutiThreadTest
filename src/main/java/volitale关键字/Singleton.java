package volitale关键字;

/**
 * Created by nick on 2018/10/4.
 * （双重检查）
 * Double-Check概念对于多线程开发者来说不会陌生，如代码中所示，
 * 我们进行了两次if (singleton == null)检查，这样就可以保证线程安全了。
 * 这样，实例化代码只用执行一次，后面再次访问时，判断if (singleton == null)，直接return实例化对象。

 优点：线程安全；延迟加载；效率较高。
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();//非原子操作，private static volatile Singleton singleton;（禁止指令重排序）
                    /*
                    memory =allocate();    //1：分配对象的内存空间
                    ctorInstance(memory);  //2：初始化对象
                    instance =memory;      //3：设置instance指向刚分配的内存地址
                     */
                }
            }
        }
        return singleton;
    }
}