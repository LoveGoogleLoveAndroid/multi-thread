package designpattern.Future.FutureData;

import java.util.concurrent.Callable;

public class Host {
    public Data request(final int count, final  char c)
    {
        System.out.println("    request(" + count + ", " + c + ") begin.");
        //final FutureData future = new FutureData();
        FutureData future = new FutureData(new Callable<RealData>() {
            @Override
            public RealData call() throws Exception {
                return new RealData(count, c);
            }
        });
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(count, c);
                future.setRealData(realData);
            }
        }).start();
        */
        new Thread(future).start();

        System.out.println("    request(" + count + ", " + c + ") end.");

        return future;
    }
}
