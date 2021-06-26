package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author aeolia
 * @Date 2021/6/23 17:24
 */
@Slf4j(topic = "c.TestBiased2")
public class TestBiased2 {

    static final Object obj = new Object();
    static Thread t1, t2, t3;

    public static void main(String[] args) {
//        Dog dog = new Dog();
//
//        new Thread(() -> {
//            log.debug(ClassLayout.parseInstance(dog).toPrintable());
//            synchronized (dog) {
//                log.debug(ClassLayout.parseInstance(dog).toPrintable());
//            }
//            log.debug(ClassLayout.parseInstance(dog).toPrintable());
//
//            synchronized (TestBiased2.class) {
//                TestBiased2.class.notify();
//            }
//        }, "t1").start();
//
//        new Thread(() -> {
//            synchronized (TestBiased2.class) {
//                try {
//                    TestBiased2.class.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            log.debug(ClassLayout.parseInstance(dog).toPrintable());
//            synchronized (dog) {
//                log.debug(ClassLayout.parseInstance(dog).toPrintable());
//            }
//            log.debug(ClassLayout.parseInstance(dog).toPrintable());
//        }, "t2").start();

        try {
            test4();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m1() {
        synchronized (obj) {
            //同步代码块A
            m2();
        }
    }

    private static void m2() {
        synchronized (obj) {
            //同步代码块B
            m3();
        }
    }

    private static void m3() {
        synchronized (obj) {
            //同步代码块C
        }
    }

    public static void test2() throws InterruptedException {
        Dog dog = new Dog();

        Thread t1 = new Thread(() -> {
            synchronized (dog) {
                log.debug(ClassLayout.parseInstance(dog).toPrintable());
            }
            synchronized (TestBiased2.class) {
                TestBiased2.class.notify();
            }
            //如果不用wait/notify 使用join必须打开下面的注释
            //因为：t1线程不能结束，否则底层线程可能被JVM重用作为t2线程，底层线程id是一样的
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();

        new Thread(() -> {
            synchronized (TestBiased2.class) {
                try {
                    TestBiased2.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug(ClassLayout.parseInstance(dog).toPrintable());
            synchronized (dog) {
                log.debug(ClassLayout.parseInstance(dog).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(dog).toPrintable());

        }, "t2").start();

    }

    public static void test3() throws InterruptedException {
        Vector<Dog> list = new Vector<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Dog d = new Dog();
                list.add(d);
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                synchronized (list) {
                    list.notify();
                }
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (list) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("===============");
            for (int i = 0; i < 30; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t2");

        t2.start();
    }

    private static void test4() throws InterruptedException {
        Vector<Dog> list = new Vector<>();
        int loopNumber = 39;
        t1 = new Thread(() -> {
            for (int i = 0; i < loopNumber; i++) {
                Dog d = new Dog();
                list.add(d);
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
            LockSupport.unpark(t2);
        }, "t1");
        t1.start();


        t2 = new Thread(() -> {
            LockSupport.park();
            log.debug("========================");
            for (int i = 0; i < loopNumber; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
            LockSupport.unpark(t3);
        }, "t2");
        t2.start();

        t3 = new Thread(() -> {
            LockSupport.park();
            log.debug("========================");
            for (int i = 0; i < loopNumber; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t3");
        t3.start();
        t3.join();

        log.debug(ClassLayout.parseInstance(new Dog()).toPrintable());
    }

    static class Dog {

    }
}

