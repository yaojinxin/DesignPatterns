package cn.aeolia.strategy;

/**
 * @Author aeolia
 * @Date 2021/6/1 20:33
 */
public interface Comparator<T> {
    /**
     * 比较方法
     * @return
     */
    int compare(T o1,T o2);
}
