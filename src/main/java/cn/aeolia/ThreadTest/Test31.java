package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author aeolia
 * @Date 2021/7/13 9:50
 */
@Slf4j(topic = "c.Test31")
public class Test31 {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<String>("A", 0);

    public static void main(String[] args) throws InterruptedException {
        log.debug("main start...");
        String prev = ref.getReference();
        int stamp = ref.getStamp();
        other();
        Thread.sleep(1000);
        log.debug("change A->B {}", ref.compareAndSet(prev, "B", stamp, stamp + 1));
    }

    public static void other() throws InterruptedException {
        new Thread(() -> {
            String prev = ref.getReference();
            int stamp = ref.getStamp();
            log.debug("{}", stamp);
            log.debug("change A->B {}", ref.compareAndSet(prev, "B", stamp, stamp + 1));
        }, "t1").start();
        Thread.sleep(500);
        new Thread(() -> {
            String prev = ref.getReference();
            int stamp = ref.getStamp();
            log.debug("{}", stamp);
            log.debug("change B->A {}", ref.compareAndSet(prev, "A", stamp, stamp + 1));
        }, "t2").start();
    }
}
