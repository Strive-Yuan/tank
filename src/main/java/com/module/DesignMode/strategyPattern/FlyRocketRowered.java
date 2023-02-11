package com.module.DesignMode.strategyPattern;

public class FlyRocketRowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}
