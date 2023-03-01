package com.module.DesignMode.adapter.objectAdapter;

public class DuckTestDrive {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();//鸭子
        WildTurkey turkey = new WildTurkey();//火鸡
        Duck turkeyAdapter = new TurkeyAdapter(turkey);//火鸡适配器
        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println();
        System.out.println("The Duck says...");
        testDuck(turkeyAdapter);

        System.out.println();
        System.out.println("The TurkeyAdapter says...");
        testDuck(turkeyAdapter);
    }

    private static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}

