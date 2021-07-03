package cn.aeolia.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author aeolia
 * @Date 2021/7/3 23:35
 */
public class Test18 {
    static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition condition1=lock.newCondition();
        Condition condition2=lock.newCondition();

        lock.lock();
        condition1.await();


        condition1.signal();
    }

}
