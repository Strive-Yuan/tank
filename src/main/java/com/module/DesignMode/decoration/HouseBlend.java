package com.module.DesignMode.decoration;

import java.math.BigDecimal;

public class HouseBlend extends Beverage{

    public HouseBlend() {
        description = "House Blend Coffee";
    }


    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(0.89);
    }
}
