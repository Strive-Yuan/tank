package algorithm.io;

public class MainThread {


    public static void main(String[] args) {
        //1.创建一个IO Thread (一个或多个)
        SelectorThreadGroup boss = new SelectorThreadGroup(3);
        //boss有自己的线程组

        SelectorThreadGroup worker = new SelectorThreadGroup(3);
        //worker也有自己的线程组
        //2.把监听的server注册到某一个selector上
        boss.bind(9990);
        boss.setWorker(worker);

        //但是，boss得多吃有worker得引用
    }
}
