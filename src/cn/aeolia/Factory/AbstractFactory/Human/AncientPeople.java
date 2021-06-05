package cn.aeolia.Factory.AbstractFactory.Human;

import cn.aeolia.Factory.AbstractFactory.Driver.Driver;
import cn.aeolia.Factory.AbstractFactory.Driver.Horse;
import cn.aeolia.Factory.AbstractFactory.Food.Food;
import cn.aeolia.Factory.AbstractFactory.Food.VegetableSoup;
import cn.aeolia.Factory.AbstractFactory.Weapon.Sword;
import cn.aeolia.Factory.AbstractFactory.Weapon.Weapon;

/**
 * 远古人类
 * @Author aeolia
 * @Date 2021/6/3 11:09
 */
public class AncientPeople extends Human {
    public AncientPeople(Horse horse, Sword sword, VegetableSoup vegetableSoup) {
        super(horse, sword, vegetableSoup);
    }
}
