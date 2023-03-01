package com.module.DesignMode.factory.factorymethod;

import com.module.DesignMode.factory.factorymethod.ingredients.*;

public interface PizzaIngredientFactory {

    Dough createDough();

    Sauce createSauce();

    Cheese createCheese();

    Veggies[] createVeggies();

    Pepperoni createPepperoni();

    Clams createClams();
}
