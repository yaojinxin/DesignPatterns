package cn.aeolia.singleton;

/**
 * @Author aeolia
 * 与01是一个意思
 * @Date 2021/6/1 11:48
 */
public class Mgr02 {
    private static final Mgr02 INSTANCE;

    static {
        INSTANCE=new Mgr02();
    }

    private Mgr02(){}

    public static Mgr02 getInstance(){
        return INSTANCE;
    }

    public void main(){
        System.out.println("m");
    }


    public static void main(String[] args) {
        Mgr02 mgr02=new Mgr02();
        Mgr02 mgr021=new Mgr02();
        System.out.println(mgr02==mgr021);
    }
}
