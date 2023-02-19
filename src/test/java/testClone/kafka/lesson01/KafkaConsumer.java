package testClone.kafka.lesson01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaConsumer {

    @Test
    public void consumer() throws ExecutionException, InterruptedException {
        //基础配置
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "101.43.98.156:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //消费的细节
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ooxx01");
        //kafka是mq，也是一个存储
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        //自动提交配置 默认是true, 提交是异步提交, 这种自动提交的方式很容易造成丢数据或重复数据
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
//        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "true");//默认是5秒提交一次
//        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"");//POLL拉取数据,弹性,按需,拉取多少？

        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
        //订阅topic
        consumer.subscribe(List.of("strive"));

        while (true) {
            //获取数据  0-N条
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(0));
            if (!records.isEmpty()) {
                System.out.println("----------------------" + records.count() + "-----------------------");
                //以下代码的优化很重要
                for (ConsumerRecord<String, String> record : records) {
                    //consumer可以消费多个分区，但是一个分组只能给一个组里面的一个consumer消费
                    String key = record.key();
                    String value = record.value();
                    int partition = record.partition();
                    long offset = record.offset();
                    System.out.println("key:" + key + " value:" + value + "partition:" + partition + " offset:" + offset);
                }
            }
        }
    }
}
