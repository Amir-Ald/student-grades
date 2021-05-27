package com.example.finalexam;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    String name = "";
    int age = 0;
    Boolean isStudent = false;
    float math = 0;
    float programming = 0;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Boolean getStudent() {
        return isStudent;
    }

    public float getMath() {
        return math;
    }

    public float getProgramming() {
        return programming;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudent(Boolean student) {
        isStudent = student;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public void setProgramming(float programming) {
        this.programming = programming;
    }
}
