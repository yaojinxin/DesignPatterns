package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/6/10 23:03
 */
public class Vehicles {

    private String type;
    private String brand;

    public Vehicles(String type, String brand) {
        this.type = type;
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Vehicles{" +
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
