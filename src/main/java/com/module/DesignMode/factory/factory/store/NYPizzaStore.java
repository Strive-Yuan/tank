//package com.module.DesignMode.factoryPattern.factory.store;
//
//import com.module.DesignMode.factoryPattern.factory.pizza.*;
//
//public class NYPizzaStore extends PizzaStore {
//
//    @Override
//    Pizza createPizza(String type) {
//        Pizza pizza = null;
//        if ("cheese".equals(type)) {
//            pizza = new NYCheesePizza();
//        } else if ("clam".equals(type)) {
//            pizza = new NYClamPizza();
//        } else if ("veggie".equals(type)) {
//            pizza = new NYVeggiePizza();
//        }
//        return pizza;
//    }
//}
