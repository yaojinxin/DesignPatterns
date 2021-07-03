package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author aeolia
 * @Date 2021/7/4 2:01
 */
@Slf4j(topic = "c.Test22")
public class Test22 {
    public static void main(String[] args) {
        for (; ; ) {
            Thread t1 = new Thread(() -> {
                LockSupport.park();
                log.debug("1");
            }, "t1");

            Thread t2 = new Thread(() -> {
                log.debug("2");
                LockSupport.unpark(t1);
            }, "t2");

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
