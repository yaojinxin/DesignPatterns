package cn.aeolia.DesignPrinciples.OCP.achetype;


/**
 * @Author aeolia
 * @Date 2021/6/5 14:00
 */
public class OCP {
    public static void main(String[] args) {

    }

}

class GrophicEditor {
    public void drawShape(Shape s) {
        if (s instanceof Rectangle) {
            drawRectangle();
        } else if (s instanceof Circle) {
            drawCircle();
        }
    }

    public void drawCircle() {
        System.out.println("圆形");
    }

    public void drawRectangle() {
        System.out.println("矩形");
    }
}

class Shape {
}

class Rectangle extends Shape {
}

class Circle extends Shape {
}

