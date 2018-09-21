package designpattern.Future.Retriever;

public class Retriever {

    public static Content retrieve(String urlStr)
    {
        //return new SyncContentImpl(urlStr);
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
