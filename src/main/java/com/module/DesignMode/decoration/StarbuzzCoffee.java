package com.module.DesignMode.decoration;

/**
 * 装饰着模式测试类
 */
public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + " $" + espresso.cost());

        Beverage darkRoast = new DarkRoast(); //0.3
        darkRoast = new Mocha(darkRoast); //0.2
        darkRoast = new Mocha(darkRoast); //0.2
        darkRoast = new Whip(darkRoast); //0.23
        System.out.println(darkRoast.getDescription() + " $" + darkRoast.cost());

        Beverage houseBlend = new HouseBlend();
        houseBlend = new Soy(houseBlend);
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Whip(houseBlend);
        System.out.println(houseBlend.getDescription() + " $" + houseBlend.cost());

//        double i = 0.3 + 0.2 + 0.2 + 0.23;
//        System.out.println(i);
    }
}
