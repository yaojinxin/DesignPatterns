package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


/**
 * @Author aeolia
 * @Date 2021/6/18 19:18
 */

@Slf4j(topic = "c.Test4")
public class Test4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //洗水壶
            try {
                log.debug("洗水壶");
                TimeUnit.SECONDS.sleep(1);

                log.debug("烧开水");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "老王");

        Thread t2 = new Thread(() -> {
            try {
                log.debug("洗茶壶");
                TimeUnit.SECONDS.sleep(1);
                log.debug("洗茶杯");
                TimeUnit.SECONDS.sleep(2);
                log.debug("拿茶叶");
                TimeUnit.SECONDS.sleep(1);

                t1.join();
                log.debug("泡茶");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "小王");

        t1.start();
        t2.start();
    }
}
