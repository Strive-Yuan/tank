package com.module.DesignMode.factoryPattern.factorymethod.pizza;

import com.module.DesignMode.factoryPattern.factorymethod.ingredients.Pepperoni;
import com.module.DesignMode.factoryPattern.factorymethod.ingredients.Sauce;
import com.module.DesignMode.factoryPattern.factorymethod.ingredients.Veggies;
import com.module.DesignMode.factoryPattern.factorymethod.ingredients.Cheese;
import com.module.DesignMode.factoryPattern.factorymethod.ingredients.Clams;
import com.module.DesignMode.factoryPattern.factorymethod.ingredients.Dough;

public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    abstract void prepare();

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
