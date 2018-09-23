package designpattern.ActiveObject.Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class MakerClientThread extends Thread {
    private final ActiveObject activeObject;
    private final char fillchar;

    public MakerClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillchar = name.charAt(0);
    }

    @Override
    public void run() {
        try
        {
            for (int i = 0; true; i++) {
                Future<String> future = activeObject.makeString(i, fillchar);
                Thread.sleep(10);
                String value = future.get();
                System.out.println(Thread.currentThread().getName() + ": value = " + value);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(Thread.currentThread().getName()+ ":" + e);
        }
        catch (ExecutionException e)
        {
            System.out.println(Thread.currentThread().getName()+ ":" + e);
        }
        catch (RejectedExecutionException e)
        {
            System.out.println(Thread.currentThread().getName()+ ":" + e);
        }
    }
}
