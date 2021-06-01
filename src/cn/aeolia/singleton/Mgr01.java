package cn.aeolia.singleton;

/**
 * @Author aeolia
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用
 * 唯一缺点：不管用到与否，类装载时就完成实例化
 * (话说你不用的，你装在它干嘛)
 * @Date 2021/6/1 11:17
 */
public class Mgr01 {

    private static final Mgr01 INSTANCE=new Mgr01();

    /**
     * 构造方法私有化
     */
    private Mgr01(){}

    public static Mgr01 getInstance(){
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 mgr01=Mgr01.getInstance();
        Mgr01 mgr02=Mgr01.getInstance();
        System.out.println(mgr01==mgr02);
    }

}
