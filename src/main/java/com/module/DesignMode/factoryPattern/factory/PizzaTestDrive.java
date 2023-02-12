//package com.module.DesignMode.factoryPattern.factory;
//
//
//import com.module.DesignMode.factoryPattern.factory.pizza.Pizza;
//import com.module.DesignMode.factoryPattern.factory.store.ChicagoPizzaStore;
//import com.module.DesignMode.factoryPattern.factory.store.NYPizzaStore;
//
//public class PizzaTestDrive {
//    public static void main(String[] args) {
//        NYPizzaStore nyPizzaStore = new NYPizzaStore();
//        Pizza cheese = nyPizzaStore.orderPizza("cheese");
//        System.out.println("Ethan ordered a " + cheese.getName() + "\n");
//
//        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
//        Pizza cheese1 = chicagoPizzaStore.orderPizza("cheese");
//        System.out.println("Joel ordered a " + cheese1.getName() + "\n");
//    }
//}
