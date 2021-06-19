package cn.aeolia.Factory.FactoryMethod;

/**
 * @Author Aeolia
 */
public class BroomFactory {

    public Moveable createBroom(){
        System.out.println("a broom created");
        return new Broom();
    }
}
