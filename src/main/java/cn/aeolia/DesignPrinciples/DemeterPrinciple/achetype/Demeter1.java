package cn.aeolia.DesignPrinciples.DemeterPrinciple.achetype;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author aeolia
 * @Date 2021/6/8 15:47
 */
public class Demeter1 {
}

/**
 * 学校总部员工类
 */
class Employee {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

/**
 * 学院员工类
 */
class CollegeEmployee {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

/**
 * 学院员工管理类
 */
class CollegeManager {
    public List<CollegeEmployee> getAllEmployees() {
        //返回学院得所有员工
        ArrayList<CollegeEmployee> list = new ArrayList<>();
        //增加十个员工
        for (int i = 0; i < 10; i++) {
            CollegeEmployee emp = new CollegeEmployee();
            emp.setId("学院员工id=" + i);
            list.add(emp);
        }

        return list;
    }
}

/**
 * 学校总部员工管理类
 */
class SchoolManager {
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Employee employee = new Employee();
            employee.setId("学校总部员工id=" + i);
            list.add(employee);
        }

        return list;
    }

    /**
     * 该方法完成输出学校总部和学院员工信息(id)
     * @param sub
     */
    void printAllEmployees(CollegeManager sub){

        /**
         * 分析问题：
         * CollegeEmployee是个局部变量，它不是当前类得直接朋友
         * 1.将输出学院的员工方法，封装到CollegeManager
         */
        List<CollegeEmployee> list=sub.getAllEmployees();
        System.out.println("==============分公司员工===============");
        for (CollegeEmployee e : list) {
            System.out.println(e.getId());
        }
        System.out.println("==============学校总部员工===============");
        List<Employee> allEmployees = this.getAllEmployees();
        for (Employee allEmployee : allEmployees) {
            System.out.println(allEmployee.getId());
        }
    }
}

