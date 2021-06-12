package cn.aeolia.Builder;

/**
 * 指挥：核心，负责只会构建的一个工程，工程如何构建，由它决定
 * @Author aeolia
 * @Date 2021/6/10 19:24
 */
public class Director {
    /**
     * 指挥工人按照顺序建房子
     * @param builder
     * @return
     */
    public Product build(Builder builder){
        builder.buildA();
        builder.buildB();
        builder.buildC();
        builder.buildD();
        return builder.getProduct();
    }
}
