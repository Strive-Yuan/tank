package com.module.DesignMode.factoryPattern.factorymethod.store;


import com.module.DesignMode.factoryPattern.factorymethod.pizza.Pizza;

public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    abstract Pizza createPizza(String type);
}
