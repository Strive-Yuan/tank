package com.module.DesignMode.strategy;

/**
 * 策略模式
 */
public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuark();
        mallard.performFly();

        Duck modelDuck = new ModelDuck();
        modelDuck.performFly();
        modelDuck.setFiyBehavior(new FlyRocketRowered());
        modelDuck.performFly();
    }
}
