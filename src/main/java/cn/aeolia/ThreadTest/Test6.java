package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author aeolia
 * @Date 2021/6/26 18:42
 */

@Slf4j(topic = "c.Test6")
public class Test6 {
    static final Object lock=new Object();

    public static void main(String[] args) {
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
