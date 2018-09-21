package designpattern.Future.FutureData;

public class Main {

    public static void main(String[] args) {
        System.out.println("main begin.");
        Host host = new Host();
        Data data1 = host.request(10, 'A');
        Data data2 = host.request(15, 'B');
        Data data3 = host.request(20, 'C');

        System.out.println("main otherJob begin.");
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("main otherJob end.");

        System.out.println("data1 = " + data1.getContent());
        System.out.println("data2 = " + data2.getContent());
        System.out.println("data3 = " + data3.getContent());

        System.out.println("main end.");
    }

    /**
     *
     * 如果主线程睡眠时间是1s，则先调用 getContent 阻塞， 再调用 setRealData 唤醒 ，最后 getContent 返回数值
     * main begin.
     request(10, A) begin.
     request(10, A) end.
     request(15, B) begin.
     request(15, B) end.
     request(20, C) begin.
     making RealData(10, A) begin.
     request(20, C) end.
     main otherJob begin.
     making RealData(20, C) begin.
     making RealData(15, B) begin.
     main otherJob end.
     main FutureData getContent ready = false
     making RealData(10, A) end.
     Thread-0 FutureData setRealData ready = false
     data1 = AAAAAAAAAA
     main FutureData getContent ready = false
     making RealData(15, B) end.
     Thread-1 FutureData setRealData ready = false
     data2 = BBBBBBBBBBBBBBB
     main FutureData getContent ready = false
     making RealData(20, C) end.
     Thread-2 FutureData setRealData ready = false
     data3 = CCCCCCCCCCCCCCCCCCCC
     main end.
     */

    /**
     * 如果主线程睡眠时间是1s，则先调用 making RealData end，再调用 setRealData 唤醒线程 ，最后 getContent 返回数值
     *
     * main begin.
     request(10, A) begin.
     request(10, A) end.
     request(15, B) begin.
     making RealData(10, A) begin.
     request(15, B) end.
     request(20, C) begin.
     making RealData(15, B) begin.
     request(20, C) end.
     main otherJob begin.
     making RealData(20, C) begin.
     making RealData(10, A) end.
     Thread-0 FutureData setRealData ready = false
     making RealData(15, B) end.
     Thread-1 FutureData setRealData ready = false
     making RealData(20, C) end.
     Thread-2 FutureData setRealData ready = false
     main otherJob end.
     main FutureData getContent ready = true
     data1 = AAAAAAAAAA
     main FutureData getContent ready = true
     data2 = BBBBBBBBBBBBBBB
     main FutureData getContent ready = true
     data3 = CCCCCCCCCCCCCCCCCCCC
     main end.
     */

    /**
     *
     * 使用FutureTask的效果
     * main begin.
     request(10, A) begin.
     request(10, A) end.
     request(15, B) begin.
     request(15, B) end.
     request(20, C) begin.
     request(20, C) end.
     main otherJob begin.
     making RealData(15, B) begin.
     making RealData(20, C) begin.
     making RealData(10, A) begin.
     making RealData(10, A) end.
     making RealData(15, B) end.
     making RealData(20, C) end.
     main otherJob end.
     main FutureData getContent
     data1 = AAAAAAAAAA
     main FutureData getContent
     data2 = BBBBBBBBBBBBBBB
     main FutureData getContent
     data3 = CCCCCCCCCCCCCCCCCCCC
     main end.
     */
}
