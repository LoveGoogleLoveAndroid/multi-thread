package designpattern.ProducerConsumer.Cake;


public class ClearThread extends Thread {
    private final Table table;

    public ClearThread(String name, Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true)
            {
                Thread.sleep(1000);
                System.out.println("--------------------" + getName() + " clears -------------------");
                table.clear();
            }
        }
        catch (InterruptedException e)
        {
            // sleep的时候在外部如果调用interrupt停止线程，会导致该异常，在此时停止线程
            //Thread.interrupted();
            System.out.println(Thread.currentThread().getName() + " has interrupted");
            this.interrupt();
            //e.printStackTrace();
        }
    }
}
