package cn.aeolia.Demo;

/**
 * 奶箱类
 *
 * @Author aeolia
 * @Date 2021/6/11 20:28
 */
public class Box {
    private int milk;

    public Box() {
    }

    public int getMilk() {
        if (milk>0) {
            System.out.println("用户拿到第" + this.milk-- + "瓶奶");
            return milk;
        }else{
            System.out.println("消费者没有拿到牛奶");
            return 0;
        }
    }

    public void setMilk(int milk) {
        this.milk = milk;
        System.out.println("送奶工将第" + this.milk + "瓶奶放入奶箱");
    }
}
