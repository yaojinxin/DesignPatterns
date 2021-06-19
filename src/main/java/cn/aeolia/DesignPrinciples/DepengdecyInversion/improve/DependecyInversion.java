package cn.aeolia.DesignPrinciples.DepengdecyInversion.improve;

/**
 * @Author aeolia
 * @Date 2021/6/4 20:22
 */
public class DependecyInversion {
    public static void main(String[] args) {
        //客户端无需改变
        Person person=new Person();
        person.receive(new Email());
        person.receive(new Weixin());
    }
}

//定义接口
interface IReceiver{
    public String getInfo();
}

class Email implements IReceiver{

    @Override
    public String getInfo() {
        return "电子邮件息：hello world";
    }
}

class Weixin implements IReceiver{

    @Override
    public String getInfo() {
        return "微信信息：hello ok";
    }
}

/**
 * 完成Person接收消息的功能
 * 方式1完成
 * 1.简单，比较容易想到
 * 2.如果我们获取的对象是微信，短信等等，则新增类，同时Persons也要增加相应的接收方法
 * 3.解决思路：引入一个抽象的接口IReceiver,表示接收者，这样Person类与接口发生依赖
 *      因为Email，Weixin等等都数据接的范围，他们各自实现IReveiver接口就行了，这样我们就符合依赖倒置原则
 */
class Person{
    public void receive(IReceiver iReceiver){
        System.out.println(iReceiver.getInfo());
    }
}
