package cn.aeolia.ThreadTest;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author aeolia
 * @Date 2021/7/3 22:04
 */
@Slf4j(topic = "c.Test17")
public class Test17 {
    private static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("尝试获取锁");
            if (!lock.tryLock()) {
                log.debug("没有获取到锁");
                return;
            }
            try {
                log.debug("获取到锁，执行临界区代码");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");


        lock.lock();
        log.debug("获得到锁");
        t1.start();

    }
}
