package cn.aeolia.Strategy;

/**
 * @Author aeolia
 * @Date 2021/6/1 19:00
 */
public class Cat {

    private int weight,height;

    private Comparator<Cat> comparator;

    public Comparator<Cat> getComparator() {
        if (comparator == null) {
            //当外部获取比较器时判断该比较器是否有具体的实例，没有则将某个策略的比较器如CatWeightComparator设置为默认比较器
            this.setComparator(new CatWeightComparator());
        }
        return comparator;
    }

    public void setComparator(Comparator<Cat> comparator) {
        this.comparator = comparator;
    }

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
