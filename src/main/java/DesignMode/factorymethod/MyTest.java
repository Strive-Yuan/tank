package DesignMode.factorymethod;

public class MyTest {

    public static void main(String[] args) {
//        Vehicle car = new Car();
//        car.run();
//        Vehicle plane = new Plane();
//        plane.run();

        Vehicle car1 = new CarFactory().createCar();
        car1.run();
        Vehicle plane1 = new PlaneFactory().createPlane();
        plane1.run();
    }
}
