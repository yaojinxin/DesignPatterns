package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @Author aeolia
 * @Date 2021/6/22 16:32
 */

@Slf4j(topic = "c.ExerciseTransfer")
public class ExerciseTransfer {


    static Random random = new Random();

    public static void main(String[] args) {
        Account a = new Account(1000);
        Account b = new Account(1000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1500; i++) {
                a.transfer(b, randomAmount());
            }

        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();

            log.debug("合计：{}",a.getMoney()+b.getMoney());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static int randomAmount() {
        return random.nextInt(100) + 1;
    }
}

class Account {

    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Account targetAccount, int money) {
        synchronized (Account.class) {
            if (this.money >= money) {
                this.money -= money;
                targetAccount.money += money;
            }
        }
    }
}
