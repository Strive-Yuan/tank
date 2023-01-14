package com.module.DesignMode.factorymethod;

public class Plane implements Vehicle {

    @Override
    public void run() {
        System.out.println("开飞机~");
    }

    public static void main(String[] args) {
        int num = 0;
        addNum(num++);

        String str ="""
                                         
                 0: iconst_0 将int型0推送至栈顶              
                 1: istore_1 将栈顶int型数值存入第二个本地变量                        
                 2: iload_1 将第二个int型本地变量推送至栈顶                     
                 3: iinc  将指定int型变量增加指定值(如i++, i--, i+=2等)                  
                 6: istore_1 将栈顶int型数值存入第二个本地变量              
                 7: return
    
                """;
    }

    private static void addNum(int num) {
        System.out.println(num);
    }
}
