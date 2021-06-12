package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/6/11 20:32
 */
public class BoxDemo {

    public static void main(String[] args) {

        Box box =new Box();

        Producer producer=new Producer(box);

        Customer customer=new Customer(box);

        new Thread(producer).start();
        new Thread(customer).start();
    }
}
