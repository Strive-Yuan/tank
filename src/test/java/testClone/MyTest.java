package testClone;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyTest {
    public static void main(String[] args) {
//        StringBuffer str1 = new StringBuffer("good");
//        StringBuffer str2 = new StringBuffer("bad");
//        change(str1,str2);
//        System.out.println(str1);
//        System.out.println(str2);
        String str = "[\n" +
                "    {\n" +
                "        \"sp_number\": \"202208100028\",\n" +
                "        \"sp_title\": {\n" +
                "            \"data\": [\n" +
                "                {\n" +
                "                    \"text\": \"补卡申请\",\n" +
                "                    \"lang\": \"zh_CN\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"sp_description\": {\n" +
                "            \"data\": [\n" +
                "                {\n" +
                "                    \"text\": \"08/09 18:00\",\n" +
                "                    \"lang\": \"zh_CN\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "]";

    }


    private static void change(StringBuffer str11, StringBuffer str12){
        str12 = str11;
        str11 = new StringBuffer("new world");
        str12.append(" new world");
    }



}
