package cn.aeolia.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

interface DecimalAccount {
    /**
     * 方法内会启动1000个线程，每个线程做-10的操作
     * 如果初始余额为10000那么正确的结果应该是0
     *
     * @param account
     */
    static void demo(DecimalAccount account) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(() -> {
                account.withdrawal(BigDecimal.TEN);
            }));
        }
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(account.getBalance());
    }

    // 获取余额
    BigDecimal getBalance();

    // 取款
    void withdrawal(BigDecimal amount);
}

/**
 * 使用原子引用类型通过cas操作来完成多线程下取款的操作
 * 并且保证了线程安全和效率
 * 但是cas操作的使用场景不适合大量线程数下，而是线程数与CPU核心数一样的时候，可以达到最大的效率
 * 当大量的线程竞争的时候，cas操作虽然不会进入阻塞，但是因为长时间竞争CPU时间片得不到执行结果会频繁发生线程上下文切换，反而降低了效率
 *
 * @Author aeolia
 * @Date 2021/7/13 9:01
 */

@Slf4j(topic = "c.Test29")
public class Test29 {
    public static void main(String[] args) {
        DecimalAccount.demo(new DecimalAccountCas(new BigDecimal("10000")));
    }
}

class DecimalAccountCas implements DecimalAccount {

    private AtomicReference<BigDecimal> balance;

    public DecimalAccountCas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withdrawal(BigDecimal amount) {
        while (true) {
            BigDecimal prev = balance.get();
            BigDecimal next = prev.subtract(amount);
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}
