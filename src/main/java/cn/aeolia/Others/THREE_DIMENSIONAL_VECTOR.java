package cn.aeolia.Others;

import java.util.Scanner;

/**
 * 三维向量
 *
 * @Author Aeolia
 */
public class THREE_DIMENSIONAL_VECTOR {
    public static void main(String[] args) {
        boolean flag = false;
        String first, second = null, third = null;
        do {
            first = new Scanner(System.in).nextLine();
            second = new Scanner(System.in).nextLine();
            third = new Scanner(System.in).nextLine();
            if (!isConform(first)|!isConform(second)|!isConform(third)) {
                System.out.println("输入不符");
                continue;
            }
            flag = true;
        } while (!flag);

        if (flag) {
            VecCal vecCal1 = transform(first);
            VecCal vecCal2 = transform(second);
            VecCal vecCal3 = transform(third);

            VecCal resultForAdd = _add_(vecCal1, vecCal2);
            VecCal resultForSub = _sub_(vecCal1, vecCal2);
            VecCal resultForMul = _mul_(vecCal1, vecCal3);
            VecCal resultForDiv = _div_(vecCal1, vecCal3);

            System.out.println(vecCal1 + " + " + vecCal2 + " = " + resultForAdd);
            System.out.println(vecCal1 + " - " + vecCal2 + " = " + resultForSub);
            System.out.println(vecCal1 + " * " + vecCal3 + " = " + resultForMul);
            System.out.println(vecCal1 + " / " + vecCal3 + " = " + resultForDiv);
        }


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
            if (vecCal.length() == 1 && 48 <= vecCal.charAt(0) && vecCal.charAt(0) <= 57) {
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

    public static VecCal transform(String vecCal) {
        if (vecCal.length() == 1) {
            return new VecCal(Integer.valueOf(vecCal));
        } else {
            String[] split = vecCal.split(",");
            return new VecCal(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
        }
    }
}

class VecCal {
    boolean isSame=false;
    int x, y, z;

    public VecCal(int xyz) {
        this.isSame=true;
        this.x=xyz;
        this.y=xyz;
        this.z=xyz;
    }

    public VecCal() {
    }

    public VecCal(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        if (this.isSame){
            return String.valueOf(x);
        }
        return "(" + x + ", " + y + ", " + z + ')';
    }
}
