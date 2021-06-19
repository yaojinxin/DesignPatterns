package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/6/11 20:31
 */
public class Producer implements Runnable{

    private Box box;

    public Producer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {
            box.setMilk(i);
        }
    }



}
