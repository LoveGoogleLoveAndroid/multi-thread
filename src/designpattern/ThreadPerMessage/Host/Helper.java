package designpattern.ThreadPerMessage.Host;

public class Helper {
    /*
    从运行结果看，首先三个线程的结果混在一起，然后二个线程的结果混在一起，最后只有一个线程

    ABCABCACBCBACABCABCABCABCABCA
    Helper handle end Thread-0
    BCBCBCBCBCB
    Helper handle end Thread-1
    CCCCC
    Helper handle end Thread-2
*/
    public void handle(int count, char c)
    {
        System.out.println("Helper handle " + c + ", " + Thread.currentThread().getName() + ", count = " + count + " begin");

        for (int i = 0; i < count; i++) {
            slowly();
            //System.out.println(" Helper handling " + i + " " + Thread.currentThread().getName());
            System.out.print(c);
        }

        System.out.println("");
        System.out.println("Helper handle end " + Thread.currentThread().getName());
    }

    private void slowly()
    {
        try{
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {

        }
    }
}
