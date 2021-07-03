package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author aeolia
 * @Date 2021/7/2 15:22
 */
public class Test13 {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                messageQueue.put(new Message(id, "消息内容" + id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                Message message = messageQueue.take();

            }
        }, "消费者").start();


        Thread thread = new Thread(() -> {

        }, "t1");
        LockSupport.park();
        LockSupport.unpark(thread);
    }
}

//消息队列类,java 线程之间通信
@Slf4j(topic = "c.MessageQueue")
class MessageQueue {
    //消息的队列集合
    private LinkedList<Message> list = new LinkedList<>();
    //队列容量
    private int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    //获取消息
    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.debug("队列为空,消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从队列的头部获取消息并返回
            Message message = list.removeFirst();
            log.debug("已消费消息:{}", message);
            list.notifyAll();
            return message;
        }
    }

    //存入消息
    public void put(Message message) {
        synchronized (list) {
            //检查队列是否已达容量上限
            while (list.size() == capcity) {
                try {
                    log.debug("队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //将消息加入队列尾部
            log.debug("已生产消息:{}", message);
            list.addLast(message);

            list.notifyAll();
        }
    }
}

final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


}
