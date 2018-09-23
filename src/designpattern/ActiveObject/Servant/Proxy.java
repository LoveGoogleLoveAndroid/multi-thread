package designpattern.ActiveObject.Servant;

public class Proxy implements ActiveObject {
    private final ScheduleThread scheduleThread;
    private final Servant servant;

    public Proxy(ScheduleThread scheduleThread, Servant servant) {
        this.scheduleThread = scheduleThread;
        this.servant = servant;
    }

    // 由MakerClientThread的run方法不断的调用，然后添加到MethodRequest
    @Override
    public Result<String> makeString(int count, char fillchar) {
        FutureResult<String> futureResult = new FutureResult<>();
        scheduleThread.invoke(new MakeStringRequest(servant, futureResult, count, fillchar));
        return futureResult;
    }

    // 由DisplayClientThread的run方法不断的调用，然后添加到MethodRequest
    @Override
    public void displayString(String string) {
        scheduleThread.invoke(new DisplayStringRequest(servant, string));
    }
}
