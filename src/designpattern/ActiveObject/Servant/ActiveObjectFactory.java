package designpattern.ActiveObject.Servant;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject()
    {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        ScheduleThread scheduleThread = new ScheduleThread(queue);
        Proxy proxy = new Proxy(scheduleThread, servant);
        scheduleThread.start();
        return proxy;
    }
}
