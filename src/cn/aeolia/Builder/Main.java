package cn.aeolia.Builder;

/**
 * @Author aeolia
 * @Date 2021/6/10 19:26
 */
public class Main {
    public static void main(String[] args) {
        //指挥者
        Director director=new Director();
        //指挥工人完成产品
        Product product = director.build(new Worker());
        System.out.println(product);

    }
}
