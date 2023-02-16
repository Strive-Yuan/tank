package com.module.DesignMode.command;

public class Stereo {
    String type;

    public Stereo(String type) {
        this.type = type;
    }

    public void on() {
        System.out.println(type + "播放音乐！");
    }

    public void off() {
        System.out.println(type + "关闭音乐！");
    }

    public void setCd() {
        System.out.println(type + "setCd！");
    }

    public void setDvd() {
        System.out.println(type + "setDvd！");
    }

    public void serRadio() {
        System.out.println(type + "serRadio！");
    }

    public void setVolume() {
        System.out.println(type + "setVolume！");
    }

}

