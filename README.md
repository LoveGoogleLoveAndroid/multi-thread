Java中的多线程及常用设计模式

## 线程的生命周期
* 创建：通过new创建一个线程
* 就绪：通过调用start方法使得线程进入等待队列
* 运行：等待队列中的线程如果获得了CPU资源，就开始运行
* 阻塞：线程在运行中调用sleep、join、wait等方法进入阻塞状态，阻塞解除后重新进入到就绪状态
* 终止：线程运行正常结束，就进入终止状态

## 线程的五种状态
* NEW：对应创建
* RUNNABLE：对应就绪和运行
* TERMINATED：对应终止
* BLOCKED：对应阻塞，等待锁
* WAITING：对应阻塞，无限等待另一个线程执行某个动作
* TIMED_WAITING：对应阻塞，有时间限制的等待另一个线程执行某个动作

## Java中的两类线程
#### 用户线程：
运行在前台，执行具体任务，比如程序的主线程，连接网络的子线程等等
#### 守护线程：
运行在后台，为其他前台线程服务，一旦所有用户线程都结束运行，守护线程会随着JVM一起结束工作，比如数据库连接池中的监测线程，JVM虚拟机启动后的监测线程，垃圾回收线程
* 通过setDaemon(true)设置在start()方法之前可以将一般线程设置为守护线程
* 在守护线程中产生的线程也是守护线程
* 不是所有的任务都可以分配给守护线程来执行，比如读写操作或者计算逻辑

#### 通过JSTACK查看线程快照
命令：jstack -l 7926

## 线程的原子性
* 基本类型、引用类型的赋值和引用都是原子操作
* long和double的赋值和引用非原子操作
* long和double要保证原子性，可以将其放入synchronized中操作，或者声明为volatile类型
* Java提供了java.util.concurrent.atomic包，如AutomicInteger，AutomicLong等，是通过封装volatile功能而得到的类库

## 线程的可见性
要实现共享变量的可见性，必须保证两点：
* 线程修改后的共享变量能够及时从工作内存刷新到主内存
* 其他线程能够及时把共享变量最新值从主内存更新到自己的工作内存
Java语言层面支持的可见性实现方式有`synchronized`和`volatile`

#### 线程不可见的原因
1. 线程的交叉执行
2. 重排序结合线程交叉执行
3. 共享变量最新值未及时在工作内存和主内存更新

#### synchronized实现可见性
synchronized的原子性可以避免线程的交叉执行，只有一个线程访问临界区，所以重排序在单线程下也不会对结果有影响，而其本身语义可以保证共享变量可以及时更新，
不仅可以实现原子性同步（互斥锁），还能实现可见性，因为JMM关于synchronized有两条规定：
* 线程解锁前，必须把共享变量的最新值刷新到主内存中
* 线程加锁时，将清空工作内存的共享变量的值，从而使用共享变量时需要从主内存重新读取（加锁和解锁需要的时同一把锁）
线程解锁前对共享变量的修改在下次加锁时对其他线程可见，执行互斥锁的过程：
1. 获得互斥锁
2. 清空工作内存
3. 从主内存拷贝变量的最新副本到工作内存
4. 执行代码
5. 将更改后的共享变量的值刷新到主内存
6. 释放互斥锁

#### volatile实现可见性
能够保证可见性，但是不能保证符合操作的原子性，是通过加入内存屏障和禁止重排序优化来实现
* 对volatile变量执行写操作，会在写操作后加入一条store屏障指令，强制刷新主内存
* 对volatile变量执行读操作，会在读操作前加入一条load屏障指令，使得缓存失效
volatile变量每次被线程读取时，都强迫从主内存重读，当该变量发生变化时，又强迫线程将最新值刷新到主内存，这样在任何时刻，不同的线程总能看到该变量的最新值
volatile不能保证原子性，比如number++的表达式是三个步骤，这三个步骤可能被三个线程处理：
1. 读取number的值
2. 将number加1
3. 写入最新的number的值
另外，语言层面final也可以保证内存可见性

#### 保证number自增操作原子性的方法
1. 使用synchronized关键字
2. 使用ReentrantLock
3. 使用AtomicInteger

#### 安全的使用volatile
必须同时满足以下两点：
1. 对变量的写入操作不依赖其当前值
> 不满足：number++, count = count * 2等
> 满足： boolean 变量等
2. 该变量没有包含在具体其他变量的不变式中
> 不满足：low < up

#### synchronized VS volatile
* volatile不需要加锁，比synchronized更轻量级，不会阻塞线程
* 从内存可见性角度，volatile读相当于加锁，写相当于解锁
* synchronized既能保证可见性，又能保证原子性，volatile只能保证可见性，无法保证原子性
即使没有保证可见性的措施，很多时候共享变量依然能够在主内存和工作内存得到及时的更新，是因为一般只有在短时间高并发的情况下才会出现变量得不到及时
更新的情况，因为CPU在执行时会很快刷新缓存，所以一般很难看到这种问题，也难以预测

#### as-if-serial
无论如何重排序，程序执行的结果与代码顺序执行的结果一致（Java编译器，运行时和处理器都会保证Java在单线程下遵循as-if-serial语义）
## 1 Single Threaded Execution模式

Single Threaded Execution模式会降低程序性能，需要减少临界区个数，缩小临界区范围
* 进入synchronized方法，线程需要获取对象的锁，需要花费时间
* 当一个线程进入临界区处理时，其他想要进入临界区的线程就会阻塞，即线程冲突


### 死锁
满足如下3个条件时，就会发生，只要破坏其一，就可以防止死锁发生
* 存在多个共享资源
* 线程在持有某个共享资源的同时，还想获取其他共享资源
* 获取共享资源的顺序不固定

## 2 Immutable 模式
类确保实例状态保持不变，不需要用synchronized进行保护，在访问实例时不需要执行耗时的互斥处理，可以提供程序性能

## 3 Guarded Suspension模式
如果执行现在的处理有问题，就让线程进行等待，来保证实例的安全性，或也可称为guarded wait、busy wait, spin lock、polling(进行舆论调查的意思，即反复检查某个事件是否发生)等
* guarded wait：由于线程使用wait是待在等待队列中停止执行的，所以不会浪费Java虚拟机的处理时间
```
等待端的实例
while (!ready)
{
    wait();
}

唤醒端的实例
ready = true;
notifyAll();
```
* busy wait：等待端使用yield会持续运行，所以会浪费Java虚拟机的时间
```
等待端的实例
while (!ready)
{
    Thread.yield();
}

唤醒端的实例
ready = true;
```

## 4 Balking模式
如果现在不适合执行这个操作，或者没必要执行，就停止处理，直接返回

### Timeout模式
在Balking模式立刻返回和Guarded Suspension模式一直等待之间，在守护条件成立之前一直等待，一旦守护条件满足，立刻执行，如果守护条件一直不满足，则抛出超时异常

## 5 Producer-Consumer模式
生产者和消费者可以是多对多，多对一，一对一的方式，对于传递的数据的顺序，可以以队列、栈、优先级队列等方式进行
wait、sleep、join都需要等待，被notify、notifyAll唤醒，如果此时调用interrupt方法，可以终止这个等待，不用再等notify、notifyAll，直接从等待队列出来，
从而抛出InterruptedException的异常，需要注意的是在调用wait时已经释放了锁，该线程在重新获取锁之后，才会抛出InterruptedException的异常
* notify、notifyAll是Object的方法，唤醒的是该实例的等待队列中的线程，而不是指定的线程，唤醒后会继续执行wait的下一条语句，执行时必须获取实例的锁
* interrupt方法是Thread的方法，可以直接指定线程唤醒，当线程正在sleep或者wait或者join时，就抛出InterruptedException的异常，执行时不需要获取要取消线程的锁
* 调用interrupt方法并不会立即抛出异常，它只是改变了线程的中断状态而已，即线程是否被中断的状态，这个异常是在调用sleep、wait、join时这些方法内部对线程的中断状态进行检查才抛出了的

ArrayBlockingQueue-基于数组的BlockingQueue  
LinkedBlockingQueue-基于链表的BlockingQueue  
PriorityBlockingQueue-带有优先级的BlockingQueue  
DelayBlockingQueue-定时间之后才可以take的BlockingQueue  
SynchronousBlockingQueue-直接传递的BlockingQueue，如果Producer先put，在Consumer take之前，Producer一直阻塞，相反，如果Consumer先take, 在Producer put之前，Consumer一直阻塞

## 6 Read-Write Lock模式
一般来说，互斥会降低程序性能，如果把针对写入的互斥和读取的互斥分别处理，利用读取操作线程之间不会冲突的特性来提高性能，当满足以下条件时，可以考虑使用读写锁模式
* 读取操作繁重，耗时
* 读取频率比写入频率高时  
读写锁是一种逻辑锁，相对于Java语言对每个实例提供的物理锁而言

## 7 Thread-Per-Message模式
每个命令或请求分配一个线程，将委托端和执行端相分离，适用于以下情况：
* 提高响应性，缩短延迟时间
* 对操作顺序没有要求
* 不需要返回值

## 8 Worker-Thread模式
如果每次发出工作请求都要创建新线程就太浪费了，可以事先启动执行工作的线程，然后使用Producer-Consumer模式将
表示工作内容的实例传递给工作线程，而且不应该让工作线程持有每项工作固有的信息，就是说它可以处理不同的请求

## 9 Future模式
如果一个线程需要返回结果，可以使用此模式，在不用降低响应性的前提下获取处理结果

## 10 Two-Phase-Termination模式
优雅的停止线程：先发出终止请求，再终止处理
* 安全的终止线程（安全性）
* 一定会进行终止处理（生存性）
* 发出终止请求后尽快进行终止处理（响应性）

CountDownLatch：想让某个线程等待指定的线程终止时可以使用join，但是join只能是一次性的操作，如果要执行N次，可以使用倒计时门阀
CyclicBarrier： 周期性的创建出屏障，在屏障解除之前，线程无法继续前进，解除条件时到达屏障处的线程个数达到了构造函数
指定的个数，然后这些线程一起继续同时继续执行

## 11 Thread-Specific Storage模式
本身并没有处理互斥操作，会被误以为吞吐量会提高，并非如此，因为ThreadLocal可以隐含了互斥处理，而且使用ThreadLocal会带来额外的开销  
与强调吞吐量相比，该模式更加看重可复用性：
* 不改变结构即可实现程序
* 没有显示的互斥处理，编程犯错的可能性较小

## 12 Active-Object模式
综合了Producer-Consumer模式、Thread-Per-Message模式、Future模式等各种模式
* MakerClientThread: 发出请求"生成字符串"的线程
* DisplayClientThread: 发出请求"显示字符串"的线程
* ActiveObject: 定义主动对象的接口
* ActiveObjectFactory: 创建主动对象的类
* Proxy: 将方法调用转为MethondRequest对象的类（实现了ActiveObject的接口）
* ScheduleThread: 调用execute处理MethodRequest对象的线程
* ActivationQueue: 按顺序保存MethodRequest对象的类
* MethodRequest: 表示请求的抽象类
* MakeStringRequest: MethodRequest的子类，生成字符串
* DisplayStringRequest: MethodRequest的子类，显示字符串
* Result: 表示执行结果的抽象类
* FutureResult: Result的子类，在Future模式中表示执行结果的类
* RealResult: Result的子类，表示实际的执行结果的类
* Servant: 执行实际处理的类（实现了ActiveObject的接口）

如果除了Client单向调用Servant外，还希望实现双向调用（即将执行结果从Servant返回给Client），以及处理的委托顺序与执行顺序的相互独立，
就可以使用ActiveObject模式；我们将来自Client的委托实现为对Proxy的调用，Proxy会将该委托转换为ConcreteMethodRequest的一个对象，
然后通过Scheduler保存在ActivationQueue中，而实际的处理并不是在Client的线程，Scheduler通过ConcreteMethodRequest将处理委托给
Servant，为了实现双向调用，使用了Future模式，ActiveObject模式组成了一个具有以下特征的"主动对象"：
* 接收来自外部的异步请求
* 能够自由的调度请求
* 可以单线程执行实际的处理
* 可以返回执行结果
* 拥有独立的线程

在使用时，必须注意问题的粒度，即问题的大小，如"生成字符串"和"显示字符串"的粒度太小，不太适合用此模式，因为无法忽略Proxy创建
ConcreteMethodRequest，以及与ActivationQueue交互产生的性能开销，但是有利于理解该模式；当问题粒度很小时，可以使用
Guarded Suspension模式