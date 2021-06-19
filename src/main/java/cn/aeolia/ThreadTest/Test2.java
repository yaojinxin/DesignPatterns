package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author aeolia
 * @Date 2021/6/17 18:39
 */
@Slf4j(topic = "c.Test2")
public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.debug("被打断了，退出循环");
                    break;
                }
            }
        }, "t1");

        t1.start();

        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
    }
}
