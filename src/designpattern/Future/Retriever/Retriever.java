package designpattern.Future.Retriever;

public class Retriever {

    public static Content retrieve(String urlStr)
    {
        // Elapsed time = 1824 mill seconds
        //return new SyncContentImpl(urlStr);

        // Elapsed time = 1233 mill seconds
        final AsyncContentImpl future = new AsyncContentImpl();

        new Thread(){
            @Override
            public void run() {
                future.setContent(new SyncContentImpl(urlStr));
            }
        }.start();

        return future;
    }
}
