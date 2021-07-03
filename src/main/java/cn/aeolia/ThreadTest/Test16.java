package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author aeolia
 * @Date 2021/7/3 21:55
 */

@Slf4j(topic = "c.Test16")
public class Test16 {
    private static ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.debug("尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("没有获得锁");
                return;
            }
            try {
                log.debug("获取到锁");
            }finally {
                lock.unlock();
            }

        }, "t1");


        lock.lock();
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("打断 t1");
        t1.interrupt();
    }
}
