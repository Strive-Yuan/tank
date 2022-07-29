package DesignMode.Singleton;

public class MyTest {
    public static void main(String[] args) {
        LazySingleton instance = LazySingleton.getInstance();
        LazySingleton instance1 = LazySingleton.getInstance();
        System.out.println(instance == instance1);

        HungrySingleton instance2 = HungrySingleton.getInstance();
        HungrySingleton instance3 = HungrySingleton.getInstance();
        System.out.println(instance2 == instance3);
    }
}
