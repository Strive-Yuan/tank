package com.module.DesignMode.command;

public class CeilingFan {
    String type;

    public CeilingFan() {
    }

    public CeilingFan(String type) {
        this.type = type;
    }

    public void on() {
        System.out.println(type + " 风扇开启！");
    }

    public void off() {
        System.out.println(type + " 风扇关闭！");
    }
}
