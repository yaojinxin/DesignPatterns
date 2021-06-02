package cn.aeolia.Strategy;

/**
 * @Author aeolia
 * @Date 2021/6/1 19:01
 */
public class Dog {
    private int food;

    public Dog(int food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }
}
