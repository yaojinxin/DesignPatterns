package cn.aeolia.Factory.AbstractFactory.Factory;

import cn.aeolia.Factory.AbstractFactory.Driver.Broom;
import cn.aeolia.Factory.AbstractFactory.Driver.Driver;
import cn.aeolia.Factory.AbstractFactory.Food.Food;
import cn.aeolia.Factory.AbstractFactory.Food.MagicBiscuit;
import cn.aeolia.Factory.AbstractFactory.Human.Enchanter;
import cn.aeolia.Factory.AbstractFactory.Human.Human;
import cn.aeolia.Factory.AbstractFactory.Weapon.MagicStick;
import cn.aeolia.Factory.AbstractFactory.Weapon.Weapon;

/**
 * @Author aeolia
 * @Date 2021/6/3 13:38
 */
public class EnchanterFactory extends AbstractFactory {
    @Override
    public Human createHuman() {
        return new Enchanter(new Broom(),new MagicStick(),new MagicBiscuit());
    }

}
