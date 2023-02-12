package com.module.DesignMode.factoryPattern.factorymethod;

import com.module.DesignMode.factoryPattern.factorymethod.ingredients.*;

public interface PizzaIngredientFactory {

    Dough createDough();

    Sauce createSauce();

    Cheese createCheese();

    Veggies[] createVeggies();

    Pepperoni createPepperoni();

    Clams createClams();
}
