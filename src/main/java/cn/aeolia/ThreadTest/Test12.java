package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author aeolia
 * @Date 2021/7/2 14:05
 */
public class Test12 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }

        TimeUnit.SECONDS.sleep(1);

        for (Integer id : MailBoxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }
    }

}

@Slf4j(topic = "c.People")
class People extends Thread {
    @Override
    public void run() {
        GuardedObj guardedObj = MailBoxes.createGuardedObj();
        log.debug("开始收信 id:{}", guardedObj.getId());
        Object response = guardedObj.getResponse(5000);
        log.debug("收到信 id:{}, 内容 :{}", guardedObj.getId(), response);
    }
}

@Slf4j(topic = "c.Postman")
class Postman extends Thread {
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObj guardedObj = MailBoxes.getGuardedObj(id);
        log.debug("送信 id:{}, 内容:{}", id, mail);
        guardedObj.setResponse(mail);

    }
}

class MailBoxes {
    private static Map<Integer, GuardedObj> boxes = new Hashtable<>();
    private static int id = 1;

    public static synchronized int generateId() {
        return id++;
    }

    public static GuardedObj createGuardedObj() {
        GuardedObj guardedObj = new GuardedObj(generateId());
        boxes.put(guardedObj.getId(), guardedObj);
        return guardedObj;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }

    public static GuardedObj getGuardedObj(int id) {
        return boxes.remove(id);
    }
}

class GuardedObj {
    //标识
    private int id;
    private Object response;

    public GuardedObj(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getResponse(long timeOut) {
        synchronized (this){
        long beginTime = System.currentTimeMillis();

            long passedTime = 0;
            while (response == null) {
                long delayTime = timeOut-passedTime;
                if (delayTime >= 0) {
                    try {
                        this.wait(delayTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return null;
                }
                passedTime = System.currentTimeMillis() - beginTime;
            }
            return response;
        }

    }

    public void setResponse(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
