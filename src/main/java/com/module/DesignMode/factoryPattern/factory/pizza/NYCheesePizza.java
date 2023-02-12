package com.module.DesignMode.factoryPattern.factory.pizza;

import com.module.DesignMode.factoryPattern.factory.pizza.Pizza;
public class NYCheesePizza extends Pizza {
    public NYCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
        toppings.add("Crated Reggiano Cheese");
    }
}
