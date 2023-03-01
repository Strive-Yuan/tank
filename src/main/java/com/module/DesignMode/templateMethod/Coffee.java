package com.module.DesignMode.templateMethod;

import java.util.Arrays;

public class Coffee extends CaffeineBeverage{
    @Override
    void brew() {
        System.out.println("泡咖啡");
    }

    @Override
    void addCondiments() {
        System.out.println("加入奶和糖");
    }
}
