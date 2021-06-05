package cn.aeolia.Factory.AbstractFactory;

import cn.aeolia.Factory.AbstractFactory.Driver.Car;
import cn.aeolia.Factory.AbstractFactory.Factory.AbstractFactory;
import cn.aeolia.Factory.AbstractFactory.Factory.AncientPeopleFactory;
import cn.aeolia.Factory.AbstractFactory.Factory.EnchanterFactory;
import cn.aeolia.Factory.AbstractFactory.Factory.ModernManFactory;
import cn.aeolia.Factory.AbstractFactory.Food.VegetableSoup;
import cn.aeolia.Factory.AbstractFactory.Human.DesignHuman;
import cn.aeolia.Factory.AbstractFactory.Human.Human;
import cn.aeolia.Factory.AbstractFactory.Weapon.MagicStick;

import java.lang.invoke.VarHandle;

/**
 * @Author aeolia
 * @Date 2021/6/3 10:37
 */
public class Main {
    public static void main(String[] args) {

        AbstractFactory factory1 = new ModernManFactory();
        Human human = factory1.createHuman();
        human.driver.move();
        human.weapon.attack();
        human.food.printName();

        factory1.createHuman();
        human.driver.move();
        human.weapon.attack();
        human.food.printName();

        AbstractFactory factory2=new EnchanterFactory();

        AbstractFactory factory3=new AncientPeopleFactory();

        DesignHuman designHuman = new DesignHuman(new Car(), new MagicStick(), new VegetableSoup());
        designHuman.driver.move();
        designHuman.weapon.attack();
        designHuman.food.printName();
    }
}
