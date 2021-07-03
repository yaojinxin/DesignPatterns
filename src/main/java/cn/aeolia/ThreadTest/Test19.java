package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author aeolia
 * @Date 2021/7/4 0:03
 */
@Slf4j(topic = "c.Test19")
public class Test19 {
    static final ReentrantLock lock = new ReentrantLock();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) {

        Condition cigarette = lock.newCondition();
        Condition takeout = lock.newCondition();
        Thread t1 = new Thread(() -> {
            while (true) {
                log.debug("有烟没有？");
                lock.lock();
                try {
                    if (hasCigarette) {
                        log.debug("有烟了，抽完干活");
                        return;
                    } else {
                        try {
                            log.debug("没烟，再等等");
                            cigarette.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }

        }, "小南");


        Thread t2 = new Thread(() -> {
            while (true) {
                log.debug("外卖来了吗？");
                lock.lock();
                try {
                    if (hasTakeout) {
                        log.debug("外卖来了，吃完干活");
                        return;
                    } else {
                        try {
                            log.debug("没来，再等等");
                            takeout.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }, "小女");

        t1.start();
        t2.start();

        try {

            TimeUnit.SECONDS.sleep(2);
            lock.lock();
            hasCigarette = true;
            log.debug("有烟了");
            cigarette.signalAll();
            hasTakeout = true;
            log.debug("有外卖了");
            takeout.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
