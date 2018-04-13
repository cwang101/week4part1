package com.tw;

import java.util.*;

public class Library {

    static final String INFO="1. 添加学生\n" +
            "2. 生成成绩单\n" +
            "3. 退出\n" +
            "请输入你的选择（1～3）：\n" ;
   // List<Student> students=new ArrayList<>();
    Map<Integer,Student> students=new HashMap<>();
    public boolean addStudent(String message){
        String[] splits=message.split(",",-1);
        try {
            double math=Double.valueOf(splits[2].split(":",-1)[1]);
            double chinese=Double.valueOf(splits[3].split(":",-1)[1]);
            double english=Double.valueOf(splits[4].split(":",-1)[1]);
            double program=Double.valueOf(splits[5].split(":",-1)[1]);
            Grade grade=new Grade(math,chinese,english,program);
            Student student=new Student(Integer.valueOf(splits[1]),splits[0],grade);
            students.put(student.getId(),student);
            System.out.print("学生"+splits[0]+"的成绩被添加\n");
            return true;
        }catch (Exception e){
            System.out.print("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n");
            return false;
        }

    }

    public double getAve(){
        double sum=0.0f;
        for(Map.Entry<Integer,Student> entry:students.entrySet()){
            sum+=entry.getValue().getGrade().getAve();
        }
        return sum/ students.size();
    }

    public double getMid(){
        List<Double> aves=new ArrayList<>();
        for(Map.Entry<Integer,Student> entry:students.entrySet()){
            aves.add(entry.getValue().getGrade().getAve());
        }
        aves.sort(Comparator.naturalOrder());
        if(aves.size()%2==0){
            return (aves.get(aves.size()/2-1)+aves.get(aves.size()/2))/2;
        }
        return aves.get(aves.size()/2);
    }

    public boolean print(String message){
        try {
            String[] ids=message.split(",",-1);
            StringBuilder sb=new StringBuilder();
            sb.append("成绩单\n");
            sb.append("姓名|数学|语文|英语|编程|平均分|总分\n");
            sb.append("========================\n");
            for(String id :ids){
                Student student=students.get(Integer.valueOf(id));
                if(student!=null){
                    sb.append(student.getDisplay()).append("\n");
                }
            }
            sb.append("========================\n");
            sb.append("全班总分平均数：").append(getAve()).append("\n");
            sb.append("全班总分中位数：").append(getMid()).append("\n");
            System.out.print(sb.toString());
            return true;
        }catch (Exception e){
            System.out.print("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n");
            return false;
        }

    }

    public static void main(String[] args) {
        Library library=new Library();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print(INFO);
            int choose=sc.nextInt();
            sc.nextLine();
            if(choose==1){
                System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交:");
                String message=sc.nextLine();
                while(!library.addStudent(message)){
                    message=sc.nextLine();
                }
            }else if(choose==2){
                System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交:");
                String message=sc.nextLine();
                while(!library.print(message)){
                    message=sc.nextLine();
                }
            }else if(choose==3){
                return;
            }
        }

    }
}
