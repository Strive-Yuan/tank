package com.module.DesignMode.templateMethod;

public class Tea extends CaffeineBeverage{
    @Override
    void brew() {
        System.out.println("泡茶");
    }

    @Override
    void addCondiments() {
        System.out.println("加柠檬");
    }
}
