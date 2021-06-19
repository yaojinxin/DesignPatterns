package cn.aeolia.Factory.FactoryMethod;

/**
 * @Author Aeolia
 */
public class CarFactory {

    public Moveable createCar(){
        System.out.println("a car created");
        return new Car();
    }
}
