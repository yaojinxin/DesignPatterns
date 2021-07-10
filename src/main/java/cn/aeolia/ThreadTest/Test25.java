package cn.aeolia.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author aeolia
 * @Date 2021/7/4 17:57
 */
public class Test25 {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> {
            awaitSignal.print("a",a,b);
        }).start();
        new Thread(() -> {
            awaitSignal.print("b",b,c);
        }).start();
        new Thread(() -> {
            awaitSignal.print("c",c,a);
        }).start();

        Thread.sleep(1000);

        awaitSignal.lock();
        try {
            a.signalAll();
        } finally {
            awaitSignal.unlock();
        }
    }
}

class AwaitSignal extends ReentrantLock {


    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    //参数1 打印内容 参数2 进入哪一间休息室
    public void print(String str, Condition condition,Condition nextCondition) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                condition.await();
                System.out.print(str);
                nextCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}
