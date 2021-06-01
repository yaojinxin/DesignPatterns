package cn.aeolia.strategy;

/**
 * @Author aeolia
 * @Date 2021/6/1 20:59
 */
public class CatHeightComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.getHeight()- o2.getHeight()==-1?-1:o1.getHeight()-o2.getHeight()==0?0:1;
    }
}
