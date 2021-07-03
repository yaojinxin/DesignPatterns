package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author aeolia
 * @Date 2021/7/3 17:13
 */

@Slf4j(topic = "c.TestLiveLock")
public class TestLiveLock {
    static final Object lock = new Object();
    static volatile int count = 10;

    public static void main(String[] args) {

        new Thread(() -> {

            while (count > 0) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (lock) {
                        count--;
                    }
                    log.debug("count:{}", count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (count < 20) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (lock) {
                        count++;
                    }
                    log.debug("count:{}", count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
