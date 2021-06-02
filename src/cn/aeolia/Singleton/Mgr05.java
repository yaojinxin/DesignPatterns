package cn.aeolia.Singleton;

/**
 *
 * @Author aeolia
 * @Date 2021/6/1 12:12
 */
public class Mgr05 {
    private static Mgr05 INSTANCE;

    private Mgr05(){}

    public static Mgr05 getInstance(){
        if (INSTANCE == null) {
            //尝试用同步代码块的方式提高效率，但是不可行
            synchronized (Mgr05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE=new Mgr05();
            }
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr05.getInstance().hashCode())).start();
        }
    }
}
