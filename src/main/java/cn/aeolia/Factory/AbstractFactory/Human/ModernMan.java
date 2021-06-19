package cn.aeolia.Factory.AbstractFactory.Human;

import cn.aeolia.Factory.AbstractFactory.Driver.Car;
import cn.aeolia.Factory.AbstractFactory.Driver.Driver;
import cn.aeolia.Factory.AbstractFactory.Food.Bread;
import cn.aeolia.Factory.AbstractFactory.Food.Food;
import cn.aeolia.Factory.AbstractFactory.Weapon.AK47;
import cn.aeolia.Factory.AbstractFactory.Weapon.Weapon;

/**
 * 现代人类
 * @Author aeolia
 * @Date 2021/6/3 11:08
 */
public class ModernMan extends Human {
    public ModernMan(Car car, AK47 ak47, Bread bread) {
        super(car, ak47, bread);
    }
}
