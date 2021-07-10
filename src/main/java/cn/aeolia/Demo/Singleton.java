package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/7/9 13:18
 */
public final class Singleton {
    private static volatile Singleton INSTANCE = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (null == INSTANCE) {
            synchronized (Singleton.class) {
                if (null == INSTANCE) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}

