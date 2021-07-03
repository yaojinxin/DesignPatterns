package cn.aeolia.Others;

import java.util.Scanner;

/**
 * @Author aeolia
 * @Date 2021/6/28 17:01
 */
public class PatternStringMatching {
    public static void main(String[] args) {
        long startTime, overTime;
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        int N = scanner.nextInt();
        scanner.nextLine();
        String[] patterns = new String[N];
        for (int i = 0; i < N; i++) {
            patterns[i] = scanner.nextLine();
        }
        startTime = System.currentTimeMillis();
        for (String pattern : patterns) {
            while (string.length() < 1000000) {
                string += string;
            }
            while (pattern.length() < 100000) {
                pattern += pattern;
            }
            toPrint(string, pattern);
        }
        overTime = System.currentTimeMillis();
        System.out.println(overTime - startTime);

    }


    public static void toPrint(String string, String pattern) {
        if (string.contains(pattern)) {
            String substring = string.substring(string.indexOf(pattern));
            System.out.println(substring);
        } else {
            System.out.println("Not Found");
        }
    }
}
