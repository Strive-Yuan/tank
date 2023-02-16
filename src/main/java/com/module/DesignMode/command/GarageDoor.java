package com.module.DesignMode.command;

public class GarageDoor {
    String type;

    public GarageDoor(String type) {
        this.type = type;
    }


    public void on() {
        System.out.println(type + " 车库开门！");
    }

    public void off() {
        System.out.println(type + " 车库关门！");
    }
}
