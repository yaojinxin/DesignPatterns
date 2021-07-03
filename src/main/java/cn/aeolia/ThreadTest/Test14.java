package cn.aeolia.ThreadTest;

import cn.aeolia.Factory.AbstractFactory.Food.Bread;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author aeolia
 * @Date 2021/7/3 13:47
 */

@Slf4j(topic = "c.Test14")
public class Test14 {
    public static void main(String[] args) {
        BigRoom bigRoom=new BigRoom();
        new Thread(() -> {
            bigRoom.sleep();
        },"小南").start();

        new Thread(() -> {
            bigRoom.study();
        },"小女").start();

    }
}

@Slf4j(topic = "c.BigRoom")
class BigRoom{
    private final Object studyRoom=new Object();
    private final Object bedRoom=new Object();

    public void sleep(){
        synchronized (bedRoom){
            log.debug("sleeping 2小时");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void study(){
        synchronized (studyRoom){
            log.debug("study 1小时");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
