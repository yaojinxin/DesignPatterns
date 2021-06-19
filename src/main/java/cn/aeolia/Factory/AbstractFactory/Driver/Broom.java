package cn.aeolia.Factory.AbstractFactory.Driver;

/**
 * 扫帚
 * @Author aeolia
 * @Date 2021/6/3 10:56
 */
public class Broom implements Driver {
    @Override
    public void move() {
        System.out.println("Broom in flying ...");
    }
}
