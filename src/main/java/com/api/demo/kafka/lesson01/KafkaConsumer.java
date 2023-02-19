package com.api.demo.kafka.lesson01;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaConsumer {

    public static void main(String[] args) {
        //基础配置
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "101.43.98.156:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //消费的细节
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ooxx01");
        //kafka是mq，也是一个存储
        //kafka第一次启动 没有offset,此配置则获取最后一条数据，如果有offset的话以offset为主
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        //自动提交配置 默认是true, 提交是异步提交, 这种自动提交的方式很容易造成丢数据或重复数据
        //一个运行的consumer,自己会维护自己消费进度
        //一旦你开启自动提交，自动提交是异步的
        //1.还没到时间，挂了，没提交，重启一个consumer，参照offset的时候，会重复消费
        //2.一个批次的数据还没写数据库成功，但是这个批次的offset被异步提交了，此时挂了，重启一个consumer，会丢失数据
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
//        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "true");//默认是5秒提交一次
//        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"");//POLL拉取数据,弹性,按需,拉取多少？
        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
        //订阅topic  kafka的consumer会动态的负载均衡
        consumer.subscribe(List.of("strive"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                System.out.println("---------------onPartitionsRevoked------------------");
                for (TopicPartition partition : partitions) {
                    System.out.println(partition.partition());
                }
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                System.out.println("---------------onPartitionsAssigned------------------");
                for (TopicPartition partition : partitions) {
                    System.out.println(partition.partition());
                }
            }
        });

//        while (true) {
//            //获取数据  0-N条
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(0));
//            if (!records.isEmpty()) {
//                System.out.println("----------------------" + records.count() + "-----------------------");
//                //以下代码的优化很重要
//                for (ConsumerRecord<String, String> record : records) {
//                    //consumer可以消费多个分区，但是一个分组只能给一个组里面的一个consumer消费
//                    String key = record.key();
//                    String value = record.value();
//                    int partition = record.partition();
//                    long offset = record.offset();
//                    System.out.println("key:" + key + " value:" + value + "partition:" + partition + " offset:" + offset);
//                }
//            }
//        }

        while (true) {
            //微批的感觉
            /**
             * 常识：如果想多线程处理多分区
             * 每poll一次，用一个语义： 一个job启动
             * 一次job用多线程并行处理分区 且 job应该被控制是串行的
             */
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(0));
            if (!records.isEmpty()) {
                System.out.println("----------------------" + records.count() + "-----------------------");
                //每个分区里的数据都是有序的
                Set<TopicPartition> partitions = records.partitions();
                for (TopicPartition partition : partitions) {
                    List<ConsumerRecord<String, String>> pRecords = records.records(partition);
                    //在一个微批里，按分区获取poll回来的数据
                    //线性按分区处理,还可以并行按分区处理多线程的方式
                    /**
                     * 手动提交offset
                     * 1.按每条消息粒度同步提交
                     * 2.按分区粒度同步提交
                     * 3.按topic粒度同步提交
                     *
                     * 如果在多线程的情况下。
                     * 1.以上1，3的方式不用多线程
                     * 2.以上2的方式最容易想到多线程处理（kafka中offset是按分区粒度来维护offset） 此方式没有问题
                     */
                    for (ConsumerRecord<String, String> pRecord : pRecords) {
                        String key = pRecord.key();
                        String value = pRecord.value();
                        int par = pRecord.partition();
                        long offset = pRecord.offset();
                        System.out.println("key:" + key + " value:" + value + "partition:" + par + " offset:" + offset);
                        //第一种：这个是最安全的，每条提交
                        consumer.commitSync(Map.of(new TopicPartition("strive", par), new OffsetAndMetadata(offset)));
                    }
                    //第二种：按分区粒度提交
                    long offset = pRecords.get(pRecords.size() - 1).offset();
                    consumer.commitSync(Map.of(partition, new OffsetAndMetadata(offset)));
                }
                //第三种：这个就是按poll的批次提交offset, 这种按批处理的方式可能会造成部分数据重复消费（比如批中数据消费一部分，挂了，重启，又消费一次）
                consumer.commitSync();
            }
        }
    }
}
