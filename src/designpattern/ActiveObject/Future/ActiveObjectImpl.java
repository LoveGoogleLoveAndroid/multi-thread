package designpattern.ActiveObject.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ActiveObjectImpl implements ActiveObject {
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    // 有返回值的调用
    @Override
    public Future<String> makeString(int count, char fillchar) {
        class MakeStringRequest implements Callable<String>
        {
            @Override
            public String call() throws Exception {
                char[] buffer = new char[count];

                // 第一次传过来的count是0，所以buffer是空
                for (int i = 0; i < count; i++) {
                    buffer[i] = fillchar;
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e)
                    {

                    }
                }
                return new String(buffer);
            }
        }

        // submit方法有返回值
        return service.submit(new MakeStringRequest());
    }

    // 没有返回值的调用
    @Override
    public void displayString(String string) {
        class DisplayStringRequest implements Runnable
        {
            @Override
            public void run() {
                try
                {
                    System.out.println("ActiveObjectImpl displayString = " + string);
                    Thread.sleep(10);
                }
                catch (InterruptedException e)
                {

                }
            }
        }

        // execute方法没有返回值
        service.execute(new DisplayStringRequest());
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }
}
