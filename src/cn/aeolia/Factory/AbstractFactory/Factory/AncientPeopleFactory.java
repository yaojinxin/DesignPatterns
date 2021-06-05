package cn.aeolia.Factory.AbstractFactory.Factory;

import cn.aeolia.Factory.AbstractFactory.Driver.Driver;
import cn.aeolia.Factory.AbstractFactory.Driver.Horse;
import cn.aeolia.Factory.AbstractFactory.Food.VegetableSoup;
import cn.aeolia.Factory.AbstractFactory.Human.AncientPeople;
import cn.aeolia.Factory.AbstractFactory.Human.Human;
import cn.aeolia.Factory.AbstractFactory.Weapon.Sword;

/**
 * @Author aeolia
 * @Date 2021/6/3 13:39
 */
public class AncientPeopleFactory extends AbstractFactory {
    @Override
    public Human createHuman() {
        return new AncientPeople(new Horse(),new Sword(),new VegetableSoup());
    }
}
