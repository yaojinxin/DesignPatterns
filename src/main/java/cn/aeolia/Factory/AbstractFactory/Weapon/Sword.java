package cn.aeolia.Factory.AbstractFactory.Weapon;

/**
 * @Author aeolia
 * @Date 2021/6/3 11:15
 */
public class Sword implements Weapon {
    @Override
    public void attack() {
        System.out.println("Sword kankankan...");
    }
}
