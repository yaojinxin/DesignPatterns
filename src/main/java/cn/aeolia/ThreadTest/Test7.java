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
            List<String> response = (List<String>) guardedObject.getResponse();
            writeToFile(response);
            log.debug("结果的大小：{}", response.size());
        }, "t1").start();


        new Thread(() -> {
            log.debug("执行下载");
            try {
                List<String> download = Downloader.download();
                guardedObject.setResponse(download);
            } catch (IOException e) {
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


class GuardedObject {
    private Object response;

    public Object getResponse() {
        synchronized (this) {
            try {
                while (null == response) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public void setResponse(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
