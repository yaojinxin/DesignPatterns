package cn.aeolia.Singleton;

/**
 * lazy loading延迟加载 懒汉式 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 可以通过synchronized解决，但也带来了效率问题
 * @Author aeolia
 * @Date 2021/6/1 12:04
 */
public class Mgr04 {
    private static Mgr04 INSTANCE;

    private Mgr04(){}

    /**
     * synchronized锁的是调用该方法的对象，静态方法锁当前类
     * 在多线程下，这种写法会进行频繁的上下锁，影响效率
     * @return
     */
    public static synchronized Mgr04 getInstance(){
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            INSTANCE=new Mgr04();
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr04.getInstance().hashCode())).start();
        }
    }
}
