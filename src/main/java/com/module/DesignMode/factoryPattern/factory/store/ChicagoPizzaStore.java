//package com.module.DesignMode.factoryPattern.factory.store;
//
//import com.module.DesignMode.factoryPattern.factory.pizza.Pizza;
//import com.module.DesignMode.factoryPattern.factory.pizza.ChicagoCheesePizza;
//import com.module.DesignMode.factoryPattern.factory.pizza.ChicagoClamPizza;
//import com.module.DesignMode.factoryPattern.factory.pizza.ChicagoVeggiePizza;
//
//public class ChicagoPizzaStore extends PizzaStore {
//    @Override
//    Pizza createPizza(String type) {
//        Pizza pizza = null;
//        if ("cheese".equals(type)) {
//            pizza = new ChicagoCheesePizza();
//        } else if ("clam".equals(type)) {
//            pizza = new ChicagoClamPizza();
//        } else if ("veggie".equals(type)) {
//            pizza = new ChicagoVeggiePizza();
//        }
//        return pizza;
//    }
//}
