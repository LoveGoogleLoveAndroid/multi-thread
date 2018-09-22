package designpattern.ThreadSpecificStorage.TSLog;

public class Log {
    private static final ThreadLocal<TSLog> tsLogCollection = new ThreadLocal<>();

    public static void println(String s)
    {
        getTSLog().println(s);
    }

    public static void close()
    {
        getTSLog().close();
    }

    private static TSLog getTSLog()
    {
        // get的key就是调用此方法的线程啦
        TSLog tsLog = tsLogCollection.get();

        if (tsLog == null)
        {
            tsLog = new TSLog(Thread.currentThread().getName() + "_log.txt");
            tsLogCollection.set(tsLog);
        }

        return tsLog;
    }
}
