package studentsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author bnhh
 * @Description
 * @date 2026/10/22-11:46
 */
public class UserManager {

    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("zhangsan","100100290878347465",
                "1234qwer","12398973645"));
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        loop:
        while (true) {
            System.out.println("---------------欢迎来到学生信息管理系统---------------");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("3.忘记密码");
            System.out.println("4.退出");
            System.out.println("请输入你的选择：");

            String choice = sc.next();

            switch (choice) {
                case "1" -> logIn(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.out.println("退出");
                    break loop;//跳出最外层循环
                }
                default -> System.out.println("没有这个选项");
            }
        }

    }

    public static void logIn(ArrayList<User> list) {

        Scanner sc = new Scanner(System.in);
        //输入验证码
        while (true){
            String verifyCode = verifyCode();
            System.out.println("请输入验证码："+verifyCode);
            String code = sc.next();
            if(code.equals(verifyCode)){
                System.out.println("验证码正确！");
                break;
            }else {
                System.out.println("验证码错误！");
            }
        }


        System.out.println("输入用户名：");
        String userName = sc.next();
        int index = isExists(list, userName);

        if(index==-1){
            System.out.println("未找到该用户！请进行注册");
            return;
        }


        for (int i = 0; i < 3; i++) {
            System.out.println("请输入密码：");
            String password = sc.next();
            boolean isEquals = password.equals(list.get(index).getPassword());
            if (isEquals) {
                System.out.println("成功登入学生信息管理系统");
                StudentManager.startStudentSystem();
                break;
            }else {
                System.out.println("密码错误！你还有"+(3-i-1)+"次机会");
            }
            System.out.println("密码错误次数过多，登录失败");
        }
    }

    public static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);

        String userName;
        String personId;
        String phoneNumber;
        String password;

        //判断用户名是否规范
        while (true){
            System.out.println("请输入用户名：");
            userName = sc.next();
            boolean isCreate = userNameCreate(list,userName);
            if(isCreate){
                break;
            }else {
                System.out.println("用户名不符合规范，请重新填写");
            }
        }


        //判断密码是否规范
        password = passwordCreate();


        //判断身份证号是否规范
        while (true){
            System.out.println("请输入身份证号码：");
            personId = sc.next();
            boolean isCreate = personIdCreate(personId);
            if (isCreate){
                break;
            }else {
                System.out.println("身份证号码不符合规范，请重新填写");
            }
        }


        //判断手机号是否规范
        while (true){
            System.out.println("请输入手机号：");
            phoneNumber = sc.next();
            boolean isCreate = phoneNumberCreate(phoneNumber);
            if(isCreate){
                break;
            }else {
                System.out.println("手机号不符合规范，请重新填写");
            }
        }

        User user = new User(userName,personId,password,phoneNumber);
        list.add(user);
        System.out.println("注册成功！");
        printList(list);


    }

    public static void printList(ArrayList<User> list){
        for (int i = 0; i < list.size(); i++) {
            User student = list.get(i);
            System.out.println((student.getUserName() + "  " + student.getId() + "  "
                    + student.getPassword() + "  " + student.getPhoneNumber()));
        }
    }

    public static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入用户名：");

        String userName = sc.next();
        int index = isExists(list, userName);
        if(index==-1){
            System.out.println("未找到该用户");
            return;
        }

        User student =list.get(index);
        while (true) {
            System.out.println("请输入身份证号码：");
            String personid = sc.next();
            String id = student.getId();
            if (id.equals(personid)) {
                break;
            } else {
                System.out.println("身份证有误，请重新输入");
            }
        }

        while (true){
            System.out.println("请输入手机号：");
            String phoneNum = sc.next();
            String phoneNumber = student.getPhoneNumber();
            if(phoneNumber.equals(phoneNum)){
                System.out.println("成功登入，请输入新密码：");
                String newPassword = passwordCreate();
                student.setPassword(newPassword);
                break;
            }else {
                System.out.println("手机号有误，请重新输入！");
            }
        }
    }

    //检查userName是否存在，存在就返回下标
    public static int isExists(ArrayList<User> list, String userName) {
        for (int i = 0; i < list.size(); i++) {
            User student = list.get(i);
            if (student.getUserName().equals(userName)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean check(String userName) {
        int numCount = 0;
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            boolean isLower = (c <= 'z' && c >= 'a');
            boolean isUpper = (c <= 'Z' && c >= 'A');
            boolean isDigit = (c <= '9' && c >= '0');
            if (!isLower && !isUpper && !isDigit) {
                return false;
            }
            if (isDigit) {
                numCount++;
            }
        }
        if (numCount == userName.length()) {
            return false;
        }
        return true;
    }

    public static boolean isDigit(String s){
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            boolean isDigit = (c <= '9' && c >= '0');
            if(!isDigit){
                return false;
            }
        }
        return true;
    }

    public static String verifyCode(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random r = new Random();
        int index;
        String s="";
        for(int i=0;i<4;i++){
            index = r.nextInt(chars.length());
            s=s+chars.charAt(index);
        }

        int clip = r.nextInt(4);//0,1,2,3
        int number = r.nextInt(10);
        char ch = (char) (number+'0');
        String s1 = s.substring(clip);
        String s2 = s.substring(0,clip);
        s=s1+ch+s2;
        return s;
    }

    public static String passwordCreate(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入密码：");
            String password1 = sc.next();
            System.out.println("请再次输入密码：");
            String password2 = sc.next();
            if (password1.equals(password2)) {
                return password1;
            } else {
                System.out.println("两次密码必须保持一致！");
            }
        }
    }

    public  static boolean userNameCreate(ArrayList<User> list, String userName){
        //判断长度
        if (userName.length() < 3 || userName.length() > 15){
            System.out.println("用户名长度必须在3-15位");
            return false;
        }
        //判断单个字符
        if (!(check(userName))) {
            System.out.println("用户名组合需为字母加数字");
            return false;
        }
        //最后判断是否在list内
        int index = isExists(list, userName);
        if (index == -1) {
            {
                return true;
            }
        } else {
            System.out.println("用户名已存在！");
            return false;
        }
    }

    public static boolean personIdCreate(String personId) {

        if (personId.length() != 18) {
            System.out.println("长度必须为18位");
            return false;
        }

        if (personId.charAt(0) == '0') {
            System.out.println("不能以0开头");
            return false;
        }

        for (int i = 0; i < personId.length(); i++) {
            char c = personId.charAt(i);
            boolean isDigit = (c >= '0' && c <= '9');
            if (i <= 16) {
                if (!isDigit) {
                    System.out.println("前17位必须为数字");
                    return false;
                }
            } else {
                if (!isDigit && c != 'x' && c != 'X') {
                    System.out.println("最后一位不符合规范，必须为数字或x或X");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean phoneNumberCreate(String phoneNumber){
        int length = phoneNumber.length();
        if (length!=11){
            System.out.println("手机号必须为11位的数字");
            return false;
        }

        if (phoneNumber.charAt(0)=='0'){
            System.out.println("手机号不能以0开头");
            return false;
        }

        boolean digit = isDigit(phoneNumber);
        if (!digit){
            System.out.println("手机号必须全为数字");
            return false;
        }
        return true;
    }

}
