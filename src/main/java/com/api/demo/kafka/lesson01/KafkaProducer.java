package com.api.demo.kafka.lesson01;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducer {

    /**
     * 1.创建topic
     * 2.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String topic = "strive";
        Properties properties = new Properties();
        //kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic yjhui
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "101.43.98.156:9092");
        //kafka 持久化数据的mq  数据->byte[],不会对数据进行干预，双方要约定编解码
        //kafka 是一个app: 可以使用零拷贝，sendfile系统调用实现快速数据消费
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); //编解码器暂时先用默认的
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        org.apache.kafka.clients.producer.KafkaProducer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
        //此时的producer是一个提供者，面向的是broker,虽然我们使用的时候希望吧数据打到topic，此时还没关联起来

        //三种商品有三种线性的id，相同的商品最好是去一个分区里
        while (true) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ProducerRecord<String, String> record = new ProducerRecord<>(topic, "item" + j, "val" + i);
                    Future<RecordMetadata> send = producer.send(record);
                    RecordMetadata recordMetadata = send.get();
                    int partition = recordMetadata.partition();
                    long offset = recordMetadata.offset();
                    System.out.println("key:" + record.key() + "  value:" + record.value() + "  partition:" + partition + " offset:" + offset);
                }
            }
        }
    }
}
