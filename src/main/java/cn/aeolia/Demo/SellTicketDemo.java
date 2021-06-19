package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/6/11 17:01
 */
public class SellTicketDemo {
    public static void main(String[] args) {
        SellTicket sellTicket=new SellTicket();
        Thread thread1=new Thread(sellTicket,"1号窗口");
        Thread thread2=new Thread(sellTicket,"2号窗口");
        Thread thread3=new Thread(sellTicket,"3号窗口");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
