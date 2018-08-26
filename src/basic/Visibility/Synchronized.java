package basic.Visibility;

public class Synchronized {
    private boolean ready = false;
    private int result = 0;
    private int number = 1;

    public synchronized void write()
    {
        ready = true;   // 1.1
        number = 2;     // 1.2
    }

    // 四个步骤的执行顺序组合有很多种，
    // 比如 1.1 -> 1.2 -> 2.1 -> 2.1, 结果是6
    // 比如 1.1 -> 2.1 -> 2.2 -> 1.2, 结果是3
    // 比如 2.1 -> 2.2 -> 1.1 -> 1.2, 结果是0
    public synchronized void read()
    {
        if (ready)              // 2.1
        {
            result = number*3;  // 2.2
        }
        System.out.println("result = " + result);
    }

    private class ReadWriteThread extends Thread
    {
        private boolean flag;

        public ReadWriteThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag)
            {
                write();
            }
            else
            {
                read();
            }
        }
    }

    public static void main(String[] args) {
        Synchronized syn = new Synchronized();

        syn.new ReadWriteThread(true).start();
        syn.new ReadWriteThread(false).start();
    }
}
