package cn.aeolia.Demo;

/**
 * @Author aeolia
 * @Date 2021/6/10 23:29
 */
public class Main {
    public static void main(String[] args) {
        Vehicles vehicles = new Vehicles("快速公交", "大众");
        System.out.println(vehicles.getType());
        System.out.println(vehicles.getBrand());

        Bus bus1=new Bus("快速公交","奔驰",(bus)->{
            if (bus.getType().equals("快速公交")) {
                bus.setPrice(2);
            }else if (bus.getType().equals("普通公交")){
                bus.setPrice(1);
            }

            if (bus.getPrice()<0){
                throw new RuntimeException("车票价格异常");
            }
        });
        Bus bus2=new Bus("普通公交","大众",(bus)->{
            if (bus.getType().equals("快速公交")) {
                bus.setPrice(2);
            }else if (bus.getType().equals("普通公交")){
                bus.setPrice(1);
            }

            if (bus.getPrice()<0){
                throw new RuntimeException("车票价格异常");
            }
        });

        System.out.println(bus1);
        System.out.println(bus2);

    }
}
