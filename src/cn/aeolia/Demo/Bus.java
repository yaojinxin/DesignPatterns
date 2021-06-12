package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/6/10 23:03
 */
public class Bus extends Vehicles{

    private int price;

    public Bus(String type, String brand,Chargeable chargeable) {
        super(type, brand);
        chargeable.charge(this);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "price=" + price +
                "} " + super.toString();
    }
}
