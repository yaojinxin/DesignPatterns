package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author aeolia
 * @Date 2021/7/3 14:49
 */
public class TestDeadLock2 {
    public static void main(String[] args) {
        Chopstick2 c1 = new Chopstick2("1");
        Chopstick2 c2 = new Chopstick2("2");
        Chopstick2 c3 = new Chopstick2("3");
        Chopstick2 c4 = new Chopstick2("4");
        Chopstick2 c5 = new Chopstick2("5");

        new Philosopher2("苏格拉底",c1,c2).start();
        new Philosopher2("柏拉图",c2,c3).start();
        new Philosopher2("亚里士多德",c3,c4).start();
        new Philosopher2("赫拉克利特",c4,c5).start();
        new Philosopher2("阿基米德",c1,c5).start();

        //改为使用顺序锁，可以解决死锁问题
        /**
         * 改为使用顺序锁，可以解决死锁问题
         * 但也会产出饥饿问题，有的线程一直没有机会抢到锁，从而一直处于阻塞队列，而有的线程却一直很快的就拿到锁，又立即获得了CPU执行权
         * ReentrantLock与Synchronized的最大区别就是可以控制锁的获取和释放
         * 当获取不到第二把锁的时候便放开第一把锁，把第一把锁让给其他线程，所以可重入锁不通过顺序锁也可以解决当前代码中的死锁问题
         * 而ReentrantLock又可以实现公平锁机制，用公平锁机制可以解决饥饿问题，在当前代码并没有使用公平锁
         */
    }

}

@Slf4j(topic = "c.Philosopher2")
class Philosopher2 extends Thread{
    Chopstick2 left;
    Chopstick2 right;

    public Philosopher2(String name, Chopstick2 left, Chopstick2 right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true){
            synchronized (left){
                synchronized (right){
                    eat();
                }
            }
        }
    }

    private void eat(){
        log.debug("eating...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Chopstick2 {
    String name;

    public Chopstick2(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + "}";
    }
}
