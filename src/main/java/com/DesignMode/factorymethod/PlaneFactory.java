package com.DesignMode.factorymethod;

public class PlaneFactory {
    public Plane createPlane() {
        System.out.println("造飞机~");
        return new Plane();
    }
}
