package Window;

// 用Runnable方式创建线程可以避免Thread方式由于Java单继承特性带来的缺陷
// Runnable的代码可以被多个线程共享，适合于多个线程处理同一资源的情况
public class TicketThread implements Runnable{
    private int ticketsCount = 10;
    // 如果线程太多，票太少，CPU执行太快，导致有些线程卖了多张，而有些线程卖了0张
    // --操作和打印消息的中间，CPU资源可能被其他线程抢占，导致余票总数不是降序
    /*
    Window_0 sold one, now tickets remain: 9
    Window_0 sold one, now tickets remain: 6
    Window_0 sold one, now tickets remain: 5
    Window_0 sold one, now tickets remain: 3
    Window_2 sold one, now tickets remain: 7
    Window_2 sold one, now tickets remain: 1
    Window_2 sold one, now tickets remain: 0
    Window_1 sold one, now tickets remain: 8
    Window_0 sold one, now tickets remain: 2
    Window_3 sold one, now tickets remain: 3
 */
    @Override
    public void run() {
        while (ticketsCount > 0)
        {
            ticketsCount--;
            System.out.println(Thread.currentThread().getName() + " sold one, now tickets remain: " + ticketsCount);
        }
    }
}
