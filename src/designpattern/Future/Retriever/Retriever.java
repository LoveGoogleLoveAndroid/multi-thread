package designpattern.Future.Retriever;

import java.util.concurrent.Callable;

public class Retriever {

    public static Content retrieve(String urlStr)
    {
        // Elapsed time = 1824 mill seconds
        //return new SyncContentImpl(urlStr);

        // Elapsed time = 1233 mill seconds
        /*final AsyncContentImpl future = new AsyncContentImpl();

        new Thread(){
            @Override
            public void run() {
                future.setContent(new SyncContentImpl(urlStr));
            }
        }.start();*/


        // Elapsed time = 1238 mill seconds
        AsyncContentImpl future = new AsyncContentImpl(new Callable<SyncContentImpl>() {
            @Override
            public SyncContentImpl call() throws Exception {
                return new SyncContentImpl(urlStr);
            }
        });

        new Thread(future).start();

        return future;
    }
}
