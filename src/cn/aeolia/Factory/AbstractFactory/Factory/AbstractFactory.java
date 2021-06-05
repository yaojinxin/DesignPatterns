package cn.aeolia.Factory.AbstractFactory.Factory;

import cn.aeolia.Factory.AbstractFactory.Driver.Driver;
import cn.aeolia.Factory.AbstractFactory.Food.Food;
import cn.aeolia.Factory.AbstractFactory.Human.DesignHuman;
import cn.aeolia.Factory.AbstractFactory.Human.Human;
import cn.aeolia.Factory.AbstractFactory.Human.ModernMan;
import cn.aeolia.Factory.AbstractFactory.Weapon.Weapon;

/**
 * @Author aeolia
 * @Date 2021/6/3 11:17
 */
public abstract class AbstractFactory {

    public abstract Human createHuman();

    public Human createHuman(Driver driver,Weapon weapon,Food food){
        return new DesignHuman(driver,weapon,food);
    }

}
