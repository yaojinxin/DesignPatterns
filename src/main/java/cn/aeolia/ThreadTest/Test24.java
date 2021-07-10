package cn.aeolia.ThreadTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author aeolia
 * @Date 2021/7/4 16:01
 */
public class Test24 {
    int threadOrder;
    int cycles;


    public Test24(int threadOrder, int cycles) {
        this.threadOrder = threadOrder;
        this.cycles = cycles;
    }

    public static void main(String[] args) {
        Test24 lock = new Test24(1,3);

        Thread t1 = new Thread(() -> {
            lock.print("a",1,2);
        }, "t1");

        Thread t2 = new Thread(() -> {
            lock.print("b",2,3);

        }, "t2");

        Thread t3 = new Thread(() -> {
            lock.print("c",3,1);

        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }

    public void print(String str, int waitInt, int nextInt) {
        int count = 0;
        for (int i = 0; i < cycles; i++) {
            synchronized (this) {
                while (threadOrder!=waitInt){
                    try {
                        if (count==cycles) break;
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                threadOrder=nextInt;
                count++;
                this.notifyAll();
            }
        }
    }
}
