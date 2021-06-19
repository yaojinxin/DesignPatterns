package cn.aeolia.Factory.AbstractFactory.Human;

import cn.aeolia.Factory.AbstractFactory.Driver.Driver;
import cn.aeolia.Factory.AbstractFactory.Food.Food;
import cn.aeolia.Factory.AbstractFactory.Weapon.Weapon;

/**
 * 人类抽象类
 * @author aeolia
 */
public abstract class Human {
    /**
     * 驾驶载具
     */
    public Driver driver;
    /**
     * 武器
     */
    public Weapon weapon;
    /**
     * 食物
     */
    public Food food;

    public Human(Driver driver, Weapon weapon, Food food) {
        this.driver = driver;
        this.weapon = weapon;
        this.food = food;
    }


}
