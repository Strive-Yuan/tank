package com.module.DesignMode.decoration;

import java.math.BigDecimal;

public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(0.23).add(beverage.cost());
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }
}
