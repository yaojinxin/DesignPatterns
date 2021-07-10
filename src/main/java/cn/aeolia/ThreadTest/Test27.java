package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author aeolia
 * @Date 2021/7/4 20:59
 */

@Slf4j(topic = "c.Test27")
public class Test27 {
    static boolean run=true;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (run) {
            }
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            log.debug("停止");
            run=false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
