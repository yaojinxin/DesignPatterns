package cn.aeolia.ThreadTest;

/**
 * @Author Aeolia
 */
public class Test {

    public static void one(A a){
        a.method();
    }

    A a=()->{
        System.out.println("aaa");
    };

    public static void main(String[] args) {

        one(()-> System.out.println("这是函数式接口的方法实现"));

        String s;
    }
}


interface A{

    void method();
}