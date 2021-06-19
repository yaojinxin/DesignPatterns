package cn.aeolia.Factory.AbstractFactory.Food;

/**
 * 蔬菜汤
 * @Author aeolia
 * @Date 2021/6/3 10:54
 */
public class VegetableSoup implements Food{
    @Override
    public void printName() {
        System.out.println("VegetableSoup gulugulu...");
    }
}
