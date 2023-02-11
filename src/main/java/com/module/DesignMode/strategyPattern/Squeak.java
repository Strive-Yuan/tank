package com.module.DesignMode.strategyPattern;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squack");
    }
}
