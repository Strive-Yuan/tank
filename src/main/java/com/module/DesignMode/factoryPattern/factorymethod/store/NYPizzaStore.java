package com.module.DesignMode.factoryPattern.factorymethod.store;


import com.module.DesignMode.factoryPattern.factorymethod.NYPizzaIngredientFactory;
import com.module.DesignMode.factoryPattern.factorymethod.pizza.*;

public class NYPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        NYPizzaIngredientFactory nyFactory = new NYPizzaIngredientFactory();
        if ("cheese".equals(type)) {
            pizza = new CheesePizza(nyFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if ("clam".equals(type)) {
            pizza = new ClamPizza(nyFactory);
            pizza.setName("New York Style Clam Pizza");
        } else if ("veggie".equals(type)) {
            pizza = new VeggiePizza(nyFactory);
            pizza.setName("New York Style Veggie Pizza");
        } else if ("pepperoni".equals(type)) {
            pizza = new PepperoniPizza(nyFactory);
            pizza.setName("New York Style Pepperoni Pizza");
        }
        return pizza;
    }
}
