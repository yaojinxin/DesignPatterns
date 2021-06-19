package cn.aeolia.Factory.AbstractFactory.Human;

import cn.aeolia.Factory.AbstractFactory.Driver.Driver;
import cn.aeolia.Factory.AbstractFactory.Food.Food;
import cn.aeolia.Factory.AbstractFactory.Weapon.Weapon;

/**
 * @Author aeolia
 * @Date 2021/6/3 18:31
 */
public class DesignHuman extends Human{

    public DesignHuman(Driver driver, Weapon weapon, Food food) {
        super(driver, weapon, food);
    }
}
