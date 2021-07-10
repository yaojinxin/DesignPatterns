package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author aeolia
 * @Date 2021/7/4 20:17
 */

@Slf4j(topic = "c.Test26")
public class Test26 {
        static Thread t1;
        static Thread t2;
        static Thread t3;
    public static void main(String[] args) {
        ParkUnpark parkUnpark=new ParkUnpark(5);
        t1=new Thread(() -> {
            parkUnpark.print("a",t2);
        });
        t2=new Thread(() -> {
            parkUnpark.print("b",t3);
        });
        t3=new Thread(() -> {
            parkUnpark.print("c",t1);
        });
        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }
}


class ParkUnpark{

    public void print(String str,Thread nextThread){
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(nextThread);
        }
    }


    private int loopNumber;

    public ParkUnpark(int loopNumber){
        this.loopNumber=loopNumber;
    }

}
