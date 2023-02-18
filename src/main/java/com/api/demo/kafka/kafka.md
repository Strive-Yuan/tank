kafka
zookeeper:只是分布式协调
broker:kafka自有的一个角色，可以理解为就像java中jvm，是一个进程(物理进程)
       broker去zookeeper中抢锁，抢到所得就是controller
![](../../../resources/kafka/broker.png)

partition:consumer 
可以是1:1或者N:1
不可以是1:N 会破坏数据有序性

offset
老版本: 在zookeeper的内存中维护了offset(偏移量的进度) 需要持久化
  问题:zookeeper不是存储，对zookeeper不是存储不应该有过多的业务上的调用使用,会占用网卡影响性能
新版本: kafka升级后自己维护offset(在自身创建的一个topic中)
第三方: 也可以在第三方中维护offset,可以是redis,mysql

kafka重复消费
异步:5秒之内先处理数据，然后持久化offset,在处理数据时宕机了还没来得及持久化offset，重启后又拿到之前的offset重新消费了一次

kafka数据丢失
没有控制好顺序，offset持久了，但是业务写失败了