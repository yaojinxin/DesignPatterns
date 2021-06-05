package cn.aeolia.Factory.AbstractFactory.Factory;

import cn.aeolia.Factory.AbstractFactory.Driver.Car;
import cn.aeolia.Factory.AbstractFactory.Food.Bread;
import cn.aeolia.Factory.AbstractFactory.Human.Human;
import cn.aeolia.Factory.AbstractFactory.Human.ModernMan;
import cn.aeolia.Factory.AbstractFactory.Weapon.AK47;

/**
 * @Author aeolia
 * @Date 2021/6/3 13:36
 */
public class ModernManFactory extends AbstractFactory {
    @Override
    public Human createHuman() {
        return new ModernMan(new Car(),new AK47(),new Bread());
    }
}
