package cn.aeolia.Singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 * 相比Mgr01更完美
 * @Author aeolia
 * @Date 2021/6/1 12:26
 */
public class Mgr07 {

    private Mgr07(){}

    /**
     * JVM在加载类的时候只会加载外部类，只有当运行时需要用到内部类的时候才会去加载，并且JVM无论加载外部类还是内部类，在内存中只会加载一次
     * 因此将外部类的实例成员变量放在内部类中，用static修饰，就会在加载内部类时加载它，再加上final修饰，就可以变成单例
     */
    private static class Mgr07Holder{
        private final static Mgr07 INSTANCE=new Mgr07();
    }

    public static Mgr07 getInstance(){
        return Mgr07Holder.INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr07.getInstance().hashCode())).start();
        }
    }
}
