package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author aeolia
 * @Date 2021/7/6 13:43
 */
@Slf4j(topic = "c.Test28")
public class Test28 {

    //监控线程
    private Thread monitorThread;

    private volatile boolean stop = false;
    private boolean isStarted = false;

    public static void main(String[] args) throws InterruptedException {
        Test28 test = new Test28();

        test.start();
        test.start();
        test.start();
        Thread.sleep(3500);
        log.debug("停止监控");
        test.stop();
    }

    //启动监控线程
    public void start() {
        synchronized (this) {
            if (isStarted) return;
            isStarted = true;
        }
        monitorThread = new Thread(() -> {
            while (true) {
//                Thread currentThread = Thread.currentThread();
                //是否被打断
                if (stop) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    log.debug("线程被打断");
                }
            }
        }, "monitor");
        monitorThread.start();
    }

    //停止监控线程
    public void stop() {
        stop = true;
        monitorThread.interrupt();
    }
}
