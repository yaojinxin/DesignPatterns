package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author aeolia
 * @Date 2021/7/3 14:49
 */
public class TestDeadLock3 {
    public static void main(String[] args) {
        Chopstick3 c1 = new Chopstick3(true,"1");
        Chopstick3 c2 = new Chopstick3(true,"2");
        Chopstick3 c3 = new Chopstick3(true,"3");
        Chopstick3 c4 = new Chopstick3(true,"4");
        Chopstick3 c5 = new Chopstick3(true,"5");

        Philosopher3 苏格拉底 = new Philosopher3("苏格拉底", c1, c2);
        Philosopher3 柏拉图 = new Philosopher3("柏拉图", c2, c3);
        Philosopher3 亚里士多德 = new Philosopher3("亚里士多德", c3, c4);
        Philosopher3 赫拉克利特 = new Philosopher3("赫拉克利特", c4, c5);
        Philosopher3 阿基米德 = new Philosopher3("阿基米德", c5, c1);


        /**
         * 改为顺序锁后，死锁解决了，使用ReentrantLock可重入锁，也可以解决死锁问题
         *
         */
        苏格拉底.start();
        柏拉图.start();
        亚里士多德.start();
        赫拉克利特.start();
        阿基米德.start();

        try {
            TimeUnit.SECONDS.sleep(2);

            System.out.println("苏格拉底执行次数："+苏格拉底.count);
            System.out.println("柏拉图执行次数："+柏拉图.count);
            System.out.println("亚里士多德执行次数："+亚里士多德.count);
            System.out.println("赫拉克利特执行次数："+赫拉克利特.count);
            System.out.println("阿基米德执行次数："+阿基米德.count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

@Slf4j(topic = "c.Philosopher3")
class Philosopher3 extends Thread {
    int count;
    Chopstick3 left;
    Chopstick3 right;

    public Philosopher3(String name, Chopstick3 left, Chopstick3 right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            if (left.tryLock()) {
                try {
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }

            }
        }
    }

    private void eat() {
        log.debug("eating...");
        try {
            Thread.sleep(100);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Chopstick3 extends ReentrantLock {
    String name;

    public Chopstick3(boolean fair, String name) {
        super(fair);
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + "}";
    }
}
