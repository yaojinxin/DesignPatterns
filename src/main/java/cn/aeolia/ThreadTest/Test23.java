package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author aeolia
 * @Date 2021/7/4 13:51
 */
@Slf4j(topic = "c.Test23")
public class Test23 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        AtomicBoolean flag = new AtomicBoolean(false);
        Condition condition = lock.newCondition();
        Thread t1 = new Thread(() -> {
            lock.lock();//获取锁
            try {
                if (!flag.get()) {//判断t2是否执行过？执行过flag=true，没执行过flag=false
                    try {
                        condition.wait();//t2没执行过就进入等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");//执行打印输出
            } finally {
                lock.unlock();//释放锁
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            lock.lock();//获取锁
            try {
                log.debug("2");//执行打印输出
                flag.set(true);//修改执行标记，说明此t2线程已经执行过
                condition.notifyAll();//唤醒其他等待线程
            } finally {
                lock.unlock();//释放锁

            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
