package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author aeolia
 * @Date 2021/6/27 14:10
 */

@Slf4j(topic = "c.Test7")
public class Test7 {

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            log.debug("等待结果");
            List<String> response = (List<String>) guardedObject.getResponse(2000);
            if (response != null) {
                writeToFile(response);
                log.debug("结果的大小：{}", response.size());
            } else {
                log.debug("结果的大小：{}", 0);
            }
        }, "t1").start();


        new Thread(() -> {
            log.debug("执行下载");
            try {
                Thread.sleep(3000);
                List<String> download = Downloader.download();
                guardedObject.setResponse(download);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }


    public static void writeToFile(List<String> list) {
        File file = new File("C:\\Users\\63495\\Desktop\\StudentMSG.txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            for (String s : list) {
                writer.print(s);
            }
        } catch (IOException e) {
            // ... handle IO exception
        }
    }

}

@Slf4j(topic = "c.GuardedObject")
class GuardedObject {
    private Object response;

    /**
     * @param timeOut 最大等待时间
     * @return
     */
    public Object getResponse(long timeOut) {
        synchronized (this) {
            //开始时间
            long begin = System.currentTimeMillis();
            //经历时间
            long passedTime = 0;
            while (null == response) {
                try {
                    if (passedTime >= timeOut) {
                        log.debug("下载等待超时");
                        break;
                    }
                    //剩余等待时间
                    this.wait(timeOut - passedTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
            }


        }
        return response;
    }

    public void setResponse(Object response) {
        synchronized (this) {
            this.response = response;
        }
        this.notifyAll();
    }
}
