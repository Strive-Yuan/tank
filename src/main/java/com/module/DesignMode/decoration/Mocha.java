package com.module.DesignMode.decoration;

import java.math.BigDecimal;

public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(0.2).add(beverage.cost());
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

}
