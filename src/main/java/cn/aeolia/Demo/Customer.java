package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/6/11 20:32
 */
public class Customer implements Runnable {
    private Box box;


    public Customer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
                box.getMilk();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
