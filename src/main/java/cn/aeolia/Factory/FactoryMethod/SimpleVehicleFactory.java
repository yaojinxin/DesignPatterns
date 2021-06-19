package cn.aeolia.Factory.FactoryMethod;

/**
 * 简单工厂模式
 * 它的可扩展性不好
 * 需要添加生产需求的时候需要再添加一个方法
 * @Author Aeolia
 */
public class SimpleVehicleFactory {
    public Car createCar(){
        //before processing
        return new Car();
    }

    public Broom createBroom(){
        return new Broom();
    }
}
