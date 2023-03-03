package com.module.DesignMode.state;

public class SoldOutState implements State {
    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已售罄");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("未投入硬币");
    }

    @Override
    public void turnCrank() {
        System.out.println("已售罄");
    }

    @Override
    public void dispense() {
        System.out.println("已售罄");
    }
}
