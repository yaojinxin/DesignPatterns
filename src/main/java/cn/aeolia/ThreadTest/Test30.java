package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author aeolia
 * @Date 2021/7/13 9:40
 */

@Slf4j(topic = "c.Test30")
public class Test30 {
    static AtomicReference<String> ref = new AtomicReference<>("A");
    public static void main(String[] args) throws InterruptedException {
        log.debug("main start...");
        // 这个共享变量被其他线程修改过?
        String prev = ref.get();
        other();
        Thread.sleep(1000);
        log.debug("change A->B {}",ref.compareAndSet(ref.get(),"B"));
    }

    private static void other(){
        new Thread(() -> {
            log.debug("change A->B {}",ref.compareAndSet(ref.get(),"B"));
        },"t1").start();

        new Thread(() -> {
            log.debug("change B->A {}",ref.compareAndSet(ref.get(),"A"));
        },"t2").start();
    }
}
