package cn.aeolia.Strategy;

/**
 * 策略接口的具体实现，根据比较内容实现不同的策略
 * 可以将该类做成单例，也可以使用将该类对象作为实体类的成员变量
 * @Author aeolia
 * @Date 2021/6/1 20:35
 */
public class CatWeightComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.getWeight()-o2.getWeight()==-1?-1:o1.getWeight()-o2.getWeight()==0?0:1;
    }
}
