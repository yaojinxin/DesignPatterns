package cn.aeolia.ThreadTest;

import java.util.Scanner;

/**
 * 三维向量
 *
 * @Author Aeolia
 */
public class THREE_DIMENSIONAL_VECTOR {
    public static void main(String[] args) {
        boolean flag = false;
        String first,second,third;
        do {
            System.out.println("请输入第一个三维向量");
            first = new Scanner(System.in).nextLine();
            if (!isConform(first)) {
                System.out.println("输入不符");
                continue;
            }

            System.out.println("请输入第二个三维向量");
            second = new Scanner(System.in).nextLine();
            if (!isConform(second)) {
                System.out.println("输入不符");
                continue;
            }

            System.out.println("请输入第三个三维向量");
            third=new Scanner(System.in).nextLine();
            if (!isConform(third)){
                System.out.println("输入不符");
                continue;
            }
            flag=true;
        } while (!flag);



    }

    public static VecCal _add_(VecCal self, VecCal n) {
        var result = new VecCal();
        result.x = self.x + n.x;
        result.y = self.y + n.y;
        result.z = self.z + n.z;
        return result;
    }

    public static VecCal _sub_(VecCal self, VecCal n) {
        var result = new VecCal();
        result.x = self.x - n.x;
        result.y = self.y - n.y;
        result.z = self.z - n.z;
        return result;
    }

    public static VecCal _mul_(VecCal self, VecCal n) {
        var result = new VecCal();
        result.x = self.x * n.x;
        result.y = self.y * n.y;
        result.z = self.z * n.z;
        return result;
    }

    public static VecCal _div_(VecCal self, VecCal n) {
        var result = new VecCal();
        try {
            result.x = self.x / n.x;
        } catch (Exception e) {
            result.x = 0;
        }
        try {
            result.y = self.y / n.y;
        } catch (Exception e) {
            result.y = 0;
        }
        try {
            result.z = self.z / n.z;
        } catch (Exception e) {
            result.z = 0;
        }
        return result;
    }

    public static boolean isConform(String vecCal) {
        if (null != vecCal) {
            if (vecCal.length()==1&&48<=vecCal.charAt(0)&&vecCal.charAt(0)<=57){
                return true;
            }
            String[] split = vecCal.split(",");
            if (split.length == 3) {
                pointA:
                for (String s : split) {
                    if (s.length() == 1) {
                        if (48 <= s.charAt(0) && s.charAt(0) <= 57) {
                            continue pointA;
                        }
                        return false;
                    }
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}

class VecCal {
    int x, y, z;

    public VecCal() {
    }

    public VecCal(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
