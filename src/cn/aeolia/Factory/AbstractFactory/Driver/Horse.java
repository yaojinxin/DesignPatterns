package cn.aeolia.Factory.AbstractFactory.Driver;

/**
 * 马
 * @Author aeolia
 * @Date 2021/6/3 10:56
 */
public class Horse implements Driver {
    @Override
    public void move() {
        System.out.println("Horse running titatita...");
    }
}
