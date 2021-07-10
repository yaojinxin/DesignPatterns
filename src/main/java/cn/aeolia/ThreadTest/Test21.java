package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author aeolia
 * @Date 2021/7/4 1:25
 */
@Slf4j(topic = "c.Test21")
public class Test21 {
    public static void main(String[] args) {
        final Object lock = new Object();

        AtomicBoolean flag = new AtomicBoolean(false);

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                if (!flag.get()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");

            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                log.debug("2");
                flag.set(true);
                lock.notifyAll();
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
