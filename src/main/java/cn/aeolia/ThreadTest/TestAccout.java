package cn.aeolia.ThreadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

interface Accout {


    static void demo(Accout account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }

        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost:" + (end - start) / 1000_000 + "ms");
    }

    Integer getBalance();

    void withdraw(Integer amount);

}

/**
 * @Author aeolia
 * @Date 2021/7/12 18:15
 */
public class TestAccout {
    public static void main(String[] args) {
        Accout accout1 = new AccoutUnsafe(10000);
        Accout.demo(accout1);
        Accout accout = new AccountCas(10000);
        Accout.demo(accout);


    }
}

class AccoutUnsafe implements Accout {

    private volatile Integer balance;

    public AccoutUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        synchronized (this) {
            return this.balance;
        }
    }

    @Override
    public void withdraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }
}

class AccountCas implements Accout {

    private AtomicInteger balance;


    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            // 获取余额的最新值
            int prev = this.balance.get();
            // 要修改的余额
            int next = prev - amount;
            // 真正修改
            if (balance.compareAndSet(prev, next)) {
                break;
            }

        }
    }
}