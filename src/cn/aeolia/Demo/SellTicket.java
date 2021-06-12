package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/6/11 16:57
 */
public class SellTicket implements Runnable {

    private int tickets = 100;

    @Override
    public void run() {
        while (true) {
            synchronized ((Integer)tickets){
                try {
                    Thread.sleep(100);
                    if (tickets > 0) {
                        tickets -= 1;
                        System.out.println(Thread.currentThread().getName() + "卖出了一张票");
                    } else {
                        System.out.println("没票了 " + Thread.currentThread().getName() + "已停止卖票");
                        Thread.currentThread().stop();
                    }
                    System.out.println("剩余票数：" + tickets);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
