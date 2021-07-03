package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author aeolia
 * @Date 2021/7/3 14:07
 */
@Slf4j(topic = "c.Test15")
public class Test15 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Object A = new Object();
        Object B = new Object();

        new Thread(() -> {
            synchronized (A) {
                try {
                    log.debug("lock A");
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (B) {
                        log.debug("lock B");
                        log.debug("操作。。。");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (B) {
                try {
                    log.debug("lock B");
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (A){
                        log.debug("lock A");
                        log.debug("操作。。。");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
