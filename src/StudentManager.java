import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    public static void main(String[] args) throws IOException {

        ArrayList<Student> array = new ArrayList<Student>();//定义一个集合，用于接收学生对象的信息。

        Scanner sc = new Scanner(System.in);//定义Scanner对象用于接收指令信息。

        System.out.println("----------欢迎来到学生班级管理系统----------");

//建立循环使程序能够循环进行。

        while (true) {

            tip();//包含指令信息以及对应的操作。

            System.out.println("请输入一个数字进行操作：");

            int num = sc.nextInt();//根据提示，录入指令。

            switch (num) {//根据指令，选择需要进行的操作。

                case 1:

                    showlish(array);//展示学生信息

                    break;

                case 2:

                    addstudent(array);//添加学生

                    break;

                case 3:

                    modify(array);//修改学生

                    break;

                case 4:

                    deletestudent(array);//删除学生

                    break;
                case 5:
                    addgrade(array);//加学分
                    break;

                case 6:

                    savelist(array);//保存表单信息

                    System.out.println("系统已退出！");

                    System.exit(0);//退出系统的代码。

                    break;

                default:

                    System.out.println("你输入有误请重新输入！");

                    break;

            }

        }

    }

    public static void savelist(ArrayList<Student> array) throws IOException {//退出系统提示是否保存信息。

        Scanner sc = new Scanner(System.in);//定义Scanner对象用于接收指令信息。

        System.out.println("是否保留本次操作的结果！");
        System.out.println("1.保存并退出");
        System.out.println("2.不保存退出");

//建立循环使程序在输入有问题的时候能够循环进行。

        while (true) {

            int num = sc.nextInt();//获取指令信息。

            if (num == 1) {//当指令为1时，保存此次操作的结果。

                System.out.println("请输入保存数据的文件路径：");//选择一个保存文件的路径。
                System.out.println("例：d:\\XXX.txt");
                Scanner sc3 = new Scanner(System.in);
                String f = sc3.next();

                BufferedWriter bw = new BufferedWriter(new FileWriter(f));//建立对象用于接收数据。

                bw.write("学号" + " " + "姓名" + " " + "学分");//文档抬头。

                bw.newLine();//换行

                for (int i = 0; i < array.size(); i++) {//建立for循环，将集合中的数据循环录入到文档中。

                    Student s = array.get(i);//得到集合中i索引的对象。

                    bw.write(s.getid() + " " + s.getname() + " " + s.getgrade());//将集合中i索引的对象的信息数据录入到文档中。

                    bw.newLine();//换行

                }

                bw.close();//结束输入。

                break;//跳出while循环，从而结束方法。

            } else if (num == 2) {

                break;//当不保存操作结果的时候，不用进行任何操作，跳出循环即可。

            } else {

                System.out.println("你输入有误！请重新输入！");//输入错误的提示。

            }

        }

    }

    public static void tip() {//包含指令信息已经对应的操作。
        System.out.println("\t" + "\t" + "*********功能提示**********");

        System.out.println("\t" + "\t" + "\t" + "  " + "1.查看信息");

        System.out.println("\t" + "\t" + "\t" + "  " + "2.添加信息");

        System.out.println("\t" + "\t" + "\t" + "  " + "3.修改信息");

        System.out.println("\t" + "\t" + "\t" + "  " + "4.删除信息");

        System.out.println("\t" + "\t" + "\t" + "  " + "5.加分减分");

        System.out.println("\t" + "\t" + "\t" + "  " + "6.退出系统");

    }

    public static void deletestudent(ArrayList<Student> array) throws IOException {//按照录入与导入的方法修改集合当中学生的信息。

        Scanner sc = new Scanner(System.in);//定义Scanner对象用于接收指令信息。

        System.out.println("请选择删除的方法");
        System.out.println("1.导入");
        System.out.println("2.手动输入");
        System.out.println("0.返回上一步");

        while (true) {

            int num = sc.nextInt();
            if (num == 0) {
                break;
            }
            if (num == 2) {//手动输入。

                System.out.println("请输入学生学号：");
                Scanner sc2 = new Scanner(System.in);
                String code = sc2.nextLine();//学号。

                int index = judgment(array, code);

                if (index != -1) {//当索引值为true的时候，即集合中存在输入的学号的时候，根据该对象的索引删除该对象。

                    array.remove(index);

                    System.out.println("删除成功！");
                    break;

                } else {

                    System.out.println("系统中不存在该学号，请重新输入！");//不存在就循环录入。

                }

            } else if (num == 1) {//导入。
                System.out.println("提示：导入数据格式为一行一个学号");
                System.out.println("请输入文件路径：");//选择一个文件的路径。
                System.out.println("例：d:\\xxx.txt");
                String filename = sc.next();

                BufferedReader br = new BufferedReader(new FileReader(filename));//建立导入数据的对象

                String line = "";//定义字符串用于接收整行数据。

                int sum = 0;//用于接收删除的学生总数。

                while ((line = br.readLine()) != null) {//判断读取的数据是否为空。

                    String[] s = line.split(" ");//利用空格分割字符串得到Student对象的信息。

//下面的方法同上面手动录入的过程。

                    int index = judgment(array, s[0]);

                    if (index != -1) {

                        array.remove(index); //如果存在相同学号 利用返回的索引删除

                        sum++;

                    } else {

                        System.out.println("学号'" + s[0] + "不存在");

                    }

                }

                br.close();

                System.out.println("成功删除了" + sum + "位同学的信息！");

                break;

            } else {

                System.out.println("你输入有误，请重新选择删除的方法");
                System.out.println("1.导入数据 2.手动输入");

            }

        }

    }

    public static void modify(ArrayList<Student> array) throws IOException {//按照录入与导入的方法修改集合当中学生的信息。

        Scanner sc = new Scanner(System.in);

        System.out.println("请选择修改的方法");
        System.out.println("1.导入数据");
        System.out.println("2.手动输入");
        System.out.println("0.返回上一步");


        while (true) {

            int num = sc.nextInt();
            if (num == 0) {
                break;
            }
            if (num == 1) {
                System.out.println("提示：数据格式：学号 新姓名 新学分 （用空格隔开，一行一个学生数据）");
                System.out.println("请输入需要导入的文件路径：");
                System.out.println("例：d:\\XXX.txt");
                String filename = sc.next();

                BufferedReader br = new BufferedReader(new FileReader(filename));

                String line = "";

                int sum = 0;

//while循环判断是否录入完毕。

                while ((line = br.readLine()) != null) {

                    String[] s = line.split(" ");

//for循环判断是否存在录入的学号

                    int index = judgment(array, s[0]);

                    if (index == -1) {

                        System.out.println("学号'" + s[0] + "不存在");

                    } else {

                        Student st = new Student(s);

                        array.set(index, st); //利用索引覆盖修改

                        sum++;

                    }

                }

                br.close();

                System.out.println("成功修改了" + sum + "位同学的信息！");

                break;

            } else if (num == 2) {

                System.out.println("请输入学生学号：");
                Scanner sc1 = new Scanner(System.in);

                String code = sc1.nextLine();

//for循环判断是否存在录入的学号

                int index = judgment(array, code);

                if (index == -1) {

                    System.out.println("系统中不存在学号'" + code + "'，请重新输入！");

                } else {

                    System.out.println("请输入学生新姓名：");

                    String name = sc.next();

                    System.out.println("请输入学生新学分：");

                    String grade = sc.next();


                    Student s = new Student(code, name, grade);

                    array.set(index, s);

                    System.out.println("修改成功！");

                    break;

                }

            } else {

                System.out.println("你输入有误，请重新选择修改的方法（1、导入  2、手动输入）：");

            }

        }

    }

    public static void showlish(ArrayList<Student> array) {//将集合中的数据以特定格式遍历出来。

        if (array.isEmpty() == true) {

            System.out.println("系统中还未存储学生信息，请你添加之后再查看！");

            System.out.println("是否添加演示信息？(输入1添加，输入2不添加)");
            Scanner sc5 = new Scanner(System.in);

            int com = sc5.nextInt();


            while (com == 1) {
                Student cbz = new Student("73", "陈炳仲", "99");
                Student cjq = new Student("86", "陈家铨", "98");
                Student cwk = new Student("77", "陈文锴", "97");
                array.add(cbz);
                array.add(cjq);
                array.add(cwk);
                System.out.println("正在添加······");
                System.out.println("添加成功！");
                break;
            }


        } else {

            System.out.println("学号" + "    -    " + "姓名" + "    -    " + "学分");

            for (int i = 0; i < array.size(); i++) {  //遍历array表单

                Student s = array.get(i);  //获取

                System.out.println(s.getid() + "    -    " + s.getname() + "    -    " + s.getgrade());

            }

        }

    }

    public static void addstudent(ArrayList<Student> array) throws IOException {//按照录入与导入的方法addstudent的信息到集合中。

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入添加的方法");
        System.out.println("1.导入文件");
        System.out.println("2.手动输入");
        System.out.println("0.返回上一步");

        while (true) {

            int num = sc.nextInt();
            if (num == 0) {
                break;
            }

            if (num == 1) {
                System.out.println("提示：数据格式为 XX XX XX  数据用空格隔开，一行为一个学生数据");
                System.out.println("请输入文件路径：");
                System.out.println("例：d:\\XXX.txt");
                String filename = sc.next();

                BufferedReader br = new BufferedReader(new FileReader(filename));

                String line = "";

                int sum = 0;

//while循环判断是否录入完毕。

                while ((line = br.readLine()) != null) {

                    String[] s = line.split(" ");

                    int index = judgment(array, s[0]);

                    if (index != -1) {

                        System.out.println("学号'" + s[0] + "'在系统中已存在！");

                    } else {

                        Student st = new Student(s);

                        array.add(st);//将对象存入集合。

                        sum++;

                    }

                }

                br.close();

                System.out.println("成功添加了" + sum + "位同学的信息！");

                return;

            } else if (num == 2) {

                System.out.println("请输入学生学号：");

                String code = sc.next();

                int index = judgment(array, code); //判断是否存在 是则返回-1

                if (index != -1) {

                    System.out.println("学号'" + code + "'已经存在，请重新输入！");

                } else {

                    System.out.println("请输入学生姓名：");

                    String name = sc.next();

                    System.out.println("请输入学生学分：");

                    String grade = sc.next();


                    Student s = new Student(code, name, grade);

                    array.add(s);//将对象存入集合。

                    System.out.println("添加成功！");

                    return;

                }

            } else {

                System.out.println("你输入有误，请重新选择添加的方法：");

            }

        }

    }

    public static int judgment(ArrayList<Student> array, String code) {//判断集合中是否存在与code相同的学号，如果存在，返回值为索引值，不存在返回值为-1。

        for (int i = 0; i < array.size(); i++) {//遍历集合。

            if (array.get(i).getid().equals(code)) {

                return i;

            }

        }

        return -1;//不存在返回值为-1。

    }

    public static void addgrade(ArrayList<Student> array) throws IOException {

        System.out.println("请输入学生学号：");
        Scanner sc4 = new Scanner(System.in);

        String code = sc4.nextLine();

//for循环判断是否存在录入的学号

        int index = judgment(array, code);

        if (index == -1) {

            System.out.println("系统中不存在学号'" + code + "'，请重新输入！");
        } else {

            Student s = array.get(index);
            System.out.println("提示:加分则直接输入数字，减分则在数字前加减号");
            System.out.println("例:输入 5 则加五分，输入 -5 则减五分");
            System.out.println("请输入加多少分：");


            int temp1 = Integer.parseInt(sc4.next());
            int temp2 = Integer.parseInt(s.getgrade());
            int temp3 = temp1 + temp2;
            String grade = String.valueOf(temp3);


            Student s2 = new Student(code, s.getname(), grade);

            array.set(index, s2);

            System.out.println("操作成功");
            System.out.println("目前 " + s.getname() + " 的学分为:" + grade);
            System.out.println("请再接再励！");


        }

    }
}
