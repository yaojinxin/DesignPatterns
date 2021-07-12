package cn.aeolia.ThreadTest;

/**
 * @Author aeolia
 * @Date 2021/7/10 18:46
 */

public final class Singleton {

    private Singleton() {
    }
    // 问题1：属于懒汉式还是饿汉式
    public static class LazyHolder{
        static final Singleton INSTANCE = new Singleton();;
    }
    // 问题2：在创建时是否有并发问题
    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 回答1：属于饿汉式，因为外部类的实例变量在内部类中，JVM在第一次加载的时候只会加载外部类
     *          等到调用getInstance方法的时候才回去加载内部类。
     * 回答2：没有并发问题，JVM加载类只会加载一次，JVM的加载机制保证了线程安全
     */
}
