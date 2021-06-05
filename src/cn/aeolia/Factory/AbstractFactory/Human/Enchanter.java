package cn.aeolia.Factory.AbstractFactory.Human;

import cn.aeolia.Factory.AbstractFactory.Driver.Broom;
import cn.aeolia.Factory.AbstractFactory.Driver.Driver;
import cn.aeolia.Factory.AbstractFactory.Food.Food;
import cn.aeolia.Factory.AbstractFactory.Food.MagicBiscuit;
import cn.aeolia.Factory.AbstractFactory.Weapon.MagicStick;
import cn.aeolia.Factory.AbstractFactory.Weapon.Weapon;

/**
 * 魔法师类
 * @Author aeolia
 * @Date 2021/6/3 11:04
 */
public class Enchanter extends Human {

    public Enchanter(Broom broom, MagicStick magicStick, MagicBiscuit magicBiscuit) {
        super(broom, magicStick, magicBiscuit);
    }

}
