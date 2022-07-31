package DesignMode.factorymethod;

public class CarFactory {

    public Car createCar() {
        System.out.println("造车~");
        return new Car();
    }

}
