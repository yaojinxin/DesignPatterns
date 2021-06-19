package cn.aeolia.Demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @Author aeolia
 * @Date 2021/6/17 13:34
 */
public class IODemo {

    static FileWriter fileWriter;

    static {
        String filePath = "C:\\Users\\63495\\Desktop\\StudentMSG.txt";
        try {
            fileWriter = new FileWriter(filePath,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int i=0;
        while (i<3){
            System.out.println("请按照“学号-姓名:成绩1,成绩2,成绩3(符号为英文)”的格式输入学生的信息");
            String studentMSG = new Scanner(System.in).nextLine();
            if (isConform(studentMSG)){
                writeMSG(studentMSG);
                i++;
            }else{
                System.out.println("信息或格式输入有误");
            }
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isConform(String checkMsg){
        String regex="^[0-9]{6}-[\u4e00-\u9fa5a-zA-Z]+:[0-9]{1,2},[0-9]{1,2},[0-9]{1,2}";
        return Pattern.matches(regex, checkMsg);
    }

    public static void writeMSG(String... args) {
        try {
            for (String arg : args) {
                fileWriter.write(arg+"\t");
            }
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
