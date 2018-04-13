package com.tw;

public class Student {
    int id;
    String name;
    Grade grade;

    public Student(int id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getDisplay(){
        return name+"|"+grade.getDisplay();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }
}
