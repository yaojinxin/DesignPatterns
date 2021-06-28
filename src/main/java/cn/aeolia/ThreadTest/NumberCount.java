package cn.aeolia.ThreadTest;

import java.util.Scanner;

/**
 * @Author aeolia
 * @Date 2021/6/28 12:37
 */
public class NumberCount {

    public static void main(String[] args) {
        String line;
        boolean flag = false;
        do {
            System.out.println("请输入一个长度不超过80个字符的非空字符串");
            line = new Scanner(System.in).nextLine();
            if (isEligibility(line)) {
                flag = true;
            } else {
                System.out.println("输入不符合要求请重新输入");
            }
        } while (!flag);

        int numberCount = 0;
        for (char c : line.toCharArray()) {
            if (48 <= c && c <= 57) numberCount++;
        }

        System.out.println("数字的个数为：" + numberCount + "个");

    }

    public static boolean isEligibility(String line) {
        return null != line ? !"".equals(line) && line.length() != 0 && line.length() <= 80 ? true : false : false;
    }
}


