package cn.aeolia.singleton;

/**
 * 枚举类内部的每个实例都会被JVM默认加上static final，在JVM加载枚举类时保证了只初始化一次
 * 不仅解决了线程同步，还可以防止反序列化
 */
public enum Mgr08 {
    INSTANCE;

    public void m(){}

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr08.INSTANCE.hashCode())).start();
        }
    }

}
