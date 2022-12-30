redis

二进制安全:
1.redis进行与外界交互得时候永远都是字节数组，只拿字节流

管道:|
1.衔接前一个命令的输入作为后一个命令的输入
2.管道会触发创建子进程
   echo $BASHPID | more   输出子进程
   echo $$ | more   输出当前进程
   原因:$$的优先级高级|

父子进程:
1.父进程的数据，子进程可以看的到吗？  不可以   
  常规思想: 进程之间需要隔离
  在linux中  使用export，子进程可以看到父进程的变量，但是子进程对变量的修改不会影响到父进程， 若父进程进行对变量进行修改也不会影响到子进程

RDB: 时点性，RDB默认开启
1.save:手动命令出发阻塞
2.bgsave:手动命令触发非阻塞，会fork子进程进行RDB(linux系统调用fork采用copy-on-write机制)
         frok开辟子进程时，仅仅创建子进程并没有复制数据，内部父子进程用指针指向同一个内存区域，当变量改变时，在内存中为新变量的值开辟内存，父进程的
         变量指针指向新的内存地址，子进程指针保持不变
3.配置文件中save标识，实际上配置的是bgsave的规则
  save  900  1
  save  300  10
  save  60   10000
4.弊端:
   （1）不支持拉链，永远只有一个dump.rdb
   （2）丢失的数据相对多一些，时点与时点之间窗口数据容易丢失，例:8点得到一个rdb，9点要落一个rdb，挂机了
5.优点:
   （1）类似java中的序列化，恢复的速度相对快一些

AOF
1.redis的写操作记录到文件中
  BGREWRITEAOF命令重新AOF文件
  auto-aof-rewrite-percebtage 100      
  auto-aof-rewrite-min-size   64mb
  redis会记录上次重写的文件大小，当增加了百分之百并且当aof>=64mb，并且aof会进行重写
  4.0以前: 重写 删除抵消的命令 合并重复的命令，得到的也是一个纯指令的文件
  4.0以后: 重写 先将老的数据以RDB得方式写到AOF文件种，讲增量的以指令的方式append到AOF中，
          AOF是一个混合体，利用了RDB的速度快和AOF的丢失数据少的特点
2.缺点:恢复速度慢    
3.优点:丢失数据少，数据恢复的完整性好一些
4.原点: redis是内存数据库，写操作会触发io，有三个写io的级别 （1）no   （2）always  （3）everysec:
5.开启AOF: appendonly改为yes  文件名appendonly.aof    
     appendsync always: 每进行一次写操作，在写入内核buffer时会调用一次内核buffer的flush,最可靠的
     appendsync everysec: (默认级别)每秒会调用一次内核buffer的flush,数据最多丢失一个buffer，最少不丢失
     appendsync no: 在内核的buffer中当buffer满了进行刷写，可能会丢失一个buffer大小的数据
6.no-appendfsync-on-rewrite no:如果redis抛出一个子进程，子进程可能在进行rdb或者重写，此时无论哪个级别父进程都不会对buffer进行
                               flush,因为此时认为子进程在疯狂进行写操作，此时父进程不参与写，不争抢io，所以此时会丢数据，故此配置是否需要开
                               启，则要看自己场景对数据的敏感性

AOF和RDB可以同时开启，如果开启了AOF只会用AOF恢复，在4.0以后版本中，AOF中包含RDB全量，增加记录新的写操作，如果AOF文件内容以REDIS开头，则此文件
时4.0以后的混合体文件  
注: BGREWRITEAOF重写之后才会变成混合体 
混合开启配置: aof-use-rdb-preamble yes

