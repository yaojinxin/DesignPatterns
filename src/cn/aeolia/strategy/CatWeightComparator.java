package cn.aeolia.strategy;

/**
 * @Author aeolia
 * @Date 2021/6/1 20:35
 */
public class CatWeightComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.getWeight()-o2.getWeight()==-1?-1:o1.getWeight()-o2.getWeight()==0?0:1;
    }
}
