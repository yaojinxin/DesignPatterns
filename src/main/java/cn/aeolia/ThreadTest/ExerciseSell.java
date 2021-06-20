package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @Author aeolia
 * @Date 2021/6/19 21:42
 */

@Slf4j(topic = "c.ExerciseSell")
public class ExerciseSell {

    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        //模拟多人买票
        TicketWindow ticketWindow = new TicketWindow(1000);
        List<Integer> integers = new Vector<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                int sell = ticketWindow.sell(randomAmount());
                    integers.add(sell);

            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        //统计卖出的票数和剩余票数
        log.debug("余票：{}", ticketWindow.getCount());
        log.debug("卖出的票数：{}", integers.stream().mapToInt(value -> value).sum());
    }

    /**
     * 随机1~5
     *
     * @return
     */
    public static int randomAmount() {
        return random.nextInt(10) + 2;
    }
}

/**
 * 售票窗口
 */
class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    /**
     * 售票
     *
     * @param amount
     * @return
     */
    public int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }

    /**
     * 获取剩余票数
     *
     * @return
     */
    public int getCount() {
        return count;
    }
}
