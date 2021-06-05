package cn.aeolia.Factory.AbstractFactory.Driver;

/**
 * 汽车
 * @Author aeolia
 * @Date 2021/6/3 10:33
 */
public class Car implements Driver{

    @Override
    public void move() {
        System.out.println("dididi...");
    }
}
