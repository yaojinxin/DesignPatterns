package cn.aeolia.Builder;

/**
 * @Author aeolia
 * @Date 2021/6/10 19:18
 */
public abstract class Builder {
    abstract void buildA();//地基
    abstract void buildB();//钢筋工程
    abstract void buildC();//铺电线
    abstract void buildD();//粉刷
    //完工：得到产品
    abstract Product getProduct();
}
