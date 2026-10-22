package studentsystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author bnhh
 * @Description
 * @date 2026/10/22-11:46
 */
public class StudentManager {
    public static void startStudentSystem() {
        ArrayList<Student> list=new ArrayList<>();
        loop:while (true){
            System.out.println("---------------欢迎来到学生信息管理系统---------------");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查询学生");
            System.out.println("5.退出");
            System.out.println("请输入你的选择：");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();

            switch (choice){
                case "1"-> addStudent(list);
                case "2"-> deleteStudent(list);
                case "3"-> updateStudent(list);
                case "4"-> queryStudent(list);
                case "5"-> {
                    System.out.println("退出");
                    break loop;//跳出最外层循环
                }
                default-> System.out.println("没有这个选项");
            }
        }

    }

    public static void addStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生ID：");
        String id = sc.next();

        int index = isExists(list, id);
        if(index<0){
            System.out.println("请输入学生姓名：");
            String name = sc.next();

            System.out.println("请输入学生年龄：");
            int age = sc.nextInt();

            System.out.println("请输入学生家庭住址：");
            String address = sc.next();

            Student student = new Student(id,name,age,address);
            boolean success = list.add(student);
            if(success){
                System.out.println("添加成功！");
            }
        }else {
            System.out.println("id已存在，请重新输入！");
            addStudent(list);
        }
    }

    public static void deleteStudent(ArrayList<Student> list){
        System.out.println("请输入要删除的学生id:");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        int index = isExists(list, id);
        if(index>=0){
            list.remove(index);
            System.out.println("删除成功！");
        }else {
            System.out.println("id不存在");
        }
    }

    public static void updateStudent(ArrayList<Student> list){
        System.out.println("请输入要修改的学生id:");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        int index = isExists(list, id);
        if(index>=0){
            Student student = list.get(index);
            System.out.println("请输入姓名：");
            String newName = sc.next();
            student.setName(newName);

            System.out.println("请输入年龄：");
            int newAge = sc.nextInt();
            student.setAge(newAge);

            System.out.println("请输入家庭住址：");
            String newAddress = sc.next();
            student.setAddress(newAddress);

            System.out.println("修改成功！");

        }else {
            System.out.println("id不存在，请重新输入！");
            updateStudent(list);
        }
    }

    public static void queryStudent(ArrayList<Student> list){
        if(list.size()==0){
            System.out.println("当前无学生信息，请添加后再查询");
            return;
        }
        System.out.println("id  "+" 姓名  "+" 年龄  "+" 家庭住址");
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println(s.getId()+"  "+s.getName()+"  "+s.getAge()+"  "+s.getAddress());
        }
    }
    //检查id是否存在，存在就返回下标
    public static int isExists(ArrayList<Student> list,String id){
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            if(student.getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
}
