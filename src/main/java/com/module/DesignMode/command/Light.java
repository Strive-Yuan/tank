package com.module.DesignMode.command;

public class Light {
    String type;

    public Light() {
    }

    public Light(String type) {
        this.type = type;
    }

    public void on() {
        System.out.println(type + " 开灯！");
    }

    public void off() {
        System.out.println(type + " 关灯！");
    }
}
