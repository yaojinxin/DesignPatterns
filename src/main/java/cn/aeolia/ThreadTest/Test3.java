package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两阶段终止模式
 * @Author aeolia
 * @Date 2021/6/18 16:55
 */

@Slf4j(topic = "c.Test3")
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        Lock lock=new ReentrantLock();
        Thread t1 = new Thread(() -> {
            while (true) {
                lock.lock();
                if (Thread.currentThread().isInterrupted()) {
                    log.debug("线程被打断了，退出循环,释放锁");
                    lock.unlock();
                    break;
                } else {
                    try {
                        Thread.sleep(1);
                        log.debug("执行监控获取，保存监控信息");
                        lock.unlock();
                    } catch (InterruptedException e) {
                        log.debug("线程在睡眠中被打断了");
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();

        Thread.sleep(100);
        t1.interrupt();
    }
}
