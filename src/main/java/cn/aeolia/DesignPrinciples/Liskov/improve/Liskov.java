package cn.aeolia.DesignPrinciples.Liskov.improve;

/**
 * @Author aeolia
 * @Date 2021/6/5 11:47
 */
public class Liskov {
    public static void main(String[] args) {
        A a=new A();
        System.out.println("11-3="+a.function1(11,3));
        System.out.println("1-8="+a.function1(1,8));
        System.out.println("=========================");

        B b=new B();
        System.out.println("11+3="+b.function1(11,3));
        System.out.println("1+8="+b.function1(1,8));
        System.out.println("11+3+9="+b.function2(11,3));
    }
}

//创建一个更加基础的类
class Base{
    //把更加基础的方法和成员写到Base类中
    public int function1(int num1,int num2){
        return num1+num2;
    }
}

class A extends Base{
    @Override
    public int function1(int num1, int num2){
        return num1-num2;
    }
}

class B extends Base{
    //这里重写了A类的方法，可能是无意识的
//    public int function1(int a,int b){
//        return a+b;
//    }
//如何B需要A类的方法，使用组合关系
    private A a=new A();
    public int function2(int a,int b){
        return function1(a,b)+9;
    }

    public int function3(int a,int b){
        return this.a.function1(a,b)+9;
    }
}

