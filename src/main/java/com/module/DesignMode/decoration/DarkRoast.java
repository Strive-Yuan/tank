package com.module.DesignMode.decoration;

import java.math.BigDecimal;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(0.3);
    }
}
