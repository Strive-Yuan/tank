//package com.module.DesignMode.factoryPattern.factory.store;
//
//import com.module.DesignMode.factoryPattern.factory.pizza.Pizza;
//
//public abstract class PizzaStore {
//
//    public Pizza orderPizza(String type) {
//        Pizza pizza;
//        pizza = createPizza(type);
//        pizza.prepare();
//        pizza.bake();
//        pizza.cut();
//        pizza.box();
//        return pizza;
//    }
//
//    abstract Pizza createPizza(String type);
//
//
//}
