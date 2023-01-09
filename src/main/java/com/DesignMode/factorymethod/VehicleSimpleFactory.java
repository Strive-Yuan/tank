package com.DesignMode.factorymethod;

/**
 * 简单工厂的扩展性不好
 */
public class VehicleSimpleFactory {
    public Car createCar() {
        System.out.println("造车~");
        return new Car();
    }

    public Plane createPlane() {
        System.out.println("造飞机~");
        return new Plane();
    }

}
