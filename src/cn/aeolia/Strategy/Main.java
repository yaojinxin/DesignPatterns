package cn.aeolia.Strategy;

import java.util.Arrays;

/**
 * @Author aeolia
 * @Date 2021/6/1 11:15
 */
public class Main {
    public static void main(String[] args) {
        Cat[] cats = new Cat[]{new Cat(10, 5), new Cat(25, 8), new Cat(8, 9), new Cat(25, 9)};
        //按weight升序排序，如果weight相同，再按height升序排序
        Sorter.sort(cats, (o1, o2) -> o1.getWeight() - o2.getWeight() < 0 ? -1 : o1.getHeight() - o2.getHeight() < 0 ? -1 : 1);
        System.out.println(Arrays.toString(cats));
    }
}
