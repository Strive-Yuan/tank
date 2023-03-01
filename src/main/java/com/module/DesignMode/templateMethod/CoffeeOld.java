package com.module.DesignMode.templateMethod;

public class CoffeeOld {
    void prepareRecipe(){
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAndMilk();
    }

    private void addSugarAndMilk() {
        System.out.println("加糖和奶");
    }

    private void pourInCup() {
        System.out.println("倒进杯子");
    }

    private void brewCoffeeGrinds() {
        System.out.println("冲泡咖啡");
    }

    private void boilWater() {
        System.out.println("烧水");
    }
}
