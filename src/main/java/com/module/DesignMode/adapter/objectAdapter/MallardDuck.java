package com.module.DesignMode.adapter.objectAdapter;

public class MallardDuck implements Duck{
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm Flying");
    }
}
