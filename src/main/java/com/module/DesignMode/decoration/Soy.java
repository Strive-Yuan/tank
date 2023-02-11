package com.module.DesignMode.decoration;

import java.math.BigDecimal;

public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(0.50).add(beverage.cost());
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

}
