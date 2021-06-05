package cn.aeolia.DesignPrinciples.Segregation.achetype;

/**
 * 接口A有五个方法，
 * 类B、D分别实现接口A的1、2、3,1、4、5方法
 * 类A、C通过接口A分别依赖B、D
 * @Author aeolia
 * @Date 2021/6/4 19:15
 */
public class Segregation {


}

interface InterfaceA {
    void method1();
    void method2();
    void method3();
    void method4();
    void method5();
}

class B implements InterfaceA {

    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }

    @Override
    public void method4() {

    }

    @Override
    public void method5() {

    }
}

class D implements InterfaceA {

    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }

    @Override
    public void method4() {

    }

    @Override
    public void method5() {

    }
}

class A{
    void Afunctions1(InterfaceA interfaceA){
        interfaceA.method1();
    }
    void Afunctions2(InterfaceA interfaceA){
        interfaceA.method2();
    }
    void Afunctions3(InterfaceA interfaceA){
        interfaceA.method3();
    }
}

class C{
    void Cfunctions1(InterfaceA interfaceA){
        interfaceA.method1();
    }
    void Cfunctions2(InterfaceA interfaceA){
        interfaceA.method4();
    }
    void Cfunctions3(InterfaceA interfaceA){
        interfaceA.method5();
    }
}

/**
 * 在接口隔离原则中，一类对另一个类的依赖应该建立在最小接口上
 * 虽然满足了设计要求，但是没有遵循接口隔离原则
 * 问题分析：
 * 第一：显然，在类B、D中，B的2、3方法是用不到的，D的4、5方法是用不到，但是因为实现接口A的关系，必须实现这个接口的所有方法，这样就造成了代码的冗余
 * 第二：知道问题的所在，但是不知道修改那一部分，可以分析一下出现问题位置的关系，尽可能的修改最小的范围来完成
 * 第三：问题出现在类B和接口A以及类D和接口A的实现关系中，而接口A又是两个类的共同依赖类，因此在可以修改接口A的情况下，优先对接口A做修改
 * 问题解决：
 * 对接口A进行拆分，将类B和类D对接口A依赖的部分独立成一个接口，再将剩余部分根据各自的依赖再分别独立
 */