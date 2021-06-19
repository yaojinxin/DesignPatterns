package cn.aeolia.Factory.AbstractFactory.Weapon;

/**
 * @Author aeolia
 * @Date 2021/6/3 11:14
 */
public class MagicStick implements Weapon {
    @Override
    public void attack() {
        System.out.println("MagicStick diandiandian...");
    }
}
