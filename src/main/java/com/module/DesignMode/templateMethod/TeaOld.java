package com.module.DesignMode.templateMethod;

public class TeaOld {
    void prepareRecipe(){
        boilWater();
        brewTeaBag();
        pourInCup();
        addLemon();
    }

    private void addLemon() {
        System.out.println("加柠檬");
    }

    private void pourInCup() {
        System.out.println("倒进杯子");
    }

    private void brewTeaBag() {
        System.out.println("冲泡茶");
    }

    private void boilWater() {
        System.out.println("烧水");
    }
}
