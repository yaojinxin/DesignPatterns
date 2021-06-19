package cn.aeolia.Factory.FactoryMethod;

/**
 * @author 63495
 */
public class Main {

    public static void main(String[] args) {
        //通过工厂方法生产对象
        Moveable moveable=new CarFactory().createCar();

    }
}
