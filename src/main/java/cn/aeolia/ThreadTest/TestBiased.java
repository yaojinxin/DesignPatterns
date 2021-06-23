package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;


/**
 * @Author aeolia
 * @Date 2021/6/23 14:47
 */

@Slf4j(topic = "c.TestBiased")
public class TestBiased {


    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog();
        log.debug(ClassLayout.parseInstance(dog).toPrintable());

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2);
                    synchronized (dog) {
                        log.debug(ClassLayout.parseInstance(dog).toPrintable());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2);
                    synchronized (dog) {
                        log.debug(ClassLayout.parseInstance(dog).toPrintable());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        log.debug("====================开启t1线程====================");
        t1.start();

        Thread.sleep(20);
        log.debug("====================开启t2线程====================");
        t2.start();
        Thread.sleep(30);
        t2.stop();
        log.debug("====================停止t2线程====================");
        System.out.println("停止t2线程");
        Thread.sleep(30);
        t1.stop();
        log.debug("====================停止t1线程====================");

    }
}

class Dog {
}
