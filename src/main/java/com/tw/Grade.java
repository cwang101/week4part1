package com.tw;

public class Grade {
    double math;
    double chinese;
    double english;
    double program;
    double ave;
    double sum;

    public Grade(double math, double chinese, double english, double program) {
        this.math = math;
        this.chinese = chinese;
        this.english = english;
        this.program = program;
        sum =math+chinese+english+program;
        ave=sum/4;
    }


    public double getAve() {
        return ave;
    }

    public String getDisplay(){
        return math+"|"+chinese+"|"+english+"|"+program+"|"+ave+"|"+sum;
    }
}
