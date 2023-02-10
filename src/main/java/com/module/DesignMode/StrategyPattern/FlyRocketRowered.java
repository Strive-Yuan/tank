package com.module.DesignMode.StrategyPattern;

public class FlyRocketRowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}
