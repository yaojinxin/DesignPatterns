package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author aeolia
 * @Date 2021/6/18 21:53
 */

@Slf4j(topic = "c.Test5")
public class Test5 {
    public static void main(String[] args) {
        Room room=new Room();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        }, "t2");


        t1.start();
        t2.start();

        log.debug("{}",room.getCounter());
    }

}

class Room{
    private int counter=0;

    public void increment(){
        synchronized (this){
            counter++;
        }
    }

    public void decrement(){
        synchronized (this){
            counter--;
        }
    }

    public int getCounter() {
        synchronized (this) {
            return counter;
        }
    }


}