package cn.aeolia.Singleton;

/**
 *
 * lazy loading延迟加载
 * 懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * @Author aeolia
 * @Date 2021/6/1 11:51
 */
public class Mgr03 {

    private static Mgr03 INSTANCE;

    private Mgr03(){}

    public static Mgr03 getInstance(){
        if (null==INSTANCE){
            try {
                //让线程睡眠放大效果
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE=new Mgr03();
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(Mgr03.getInstance().hashCode())).start();
        }
        /*
        * 141318614
        * 406416341
        * 1959711354
        * 765867035
        * 1923742319
        * 1135326590
        * 1929026007
        * */
    }
}
