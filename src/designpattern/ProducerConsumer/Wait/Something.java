package designpattern.ProducerConsumer.Wait;

public class Something {
    public static void method(long x) throws InterruptedException
    {
        if (x != 0)
        {
            Object object = new Object();
            synchronized (object)
            {
                // 相当于sleep一段时间，由于该对象是内部对象，不会被外部调用notify/notifyAll唤醒，所以会完整的睡眠 x ms后自动结束
                object.wait(x);
            }
        }
    }
}
