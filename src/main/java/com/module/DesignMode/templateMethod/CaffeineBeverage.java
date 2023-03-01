package com.module.DesignMode.templateMethod;

public abstract class CaffeineBeverage {

    final void prepareRecipe() {

    }

    abstract void brew();

    abstract void addCondiments();

    private void pourInCup() {
        System.out.println("倒进杯子");
    }

    private void boilWater() {
        System.out.println("烧水");
    }

}
