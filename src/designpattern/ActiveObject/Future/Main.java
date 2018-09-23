package designpattern.ActiveObject.Future;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        try
        {
            new MakerClientThread("Alice", activeObject).start();
            new MakerClientThread("Bobby", activeObject).start();
            new DisplayClientThread("Chris", activeObject).start();

            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            System.out.println("main: " + e);
        }
        finally {
            System.out.println("main: shutdown");
            activeObject.shutdown();
        }
    }

    /**
     *
     * ActiveObjectImpl displayString = Chris 0
     Alice: value =
     Bobby: value =
     Alice: value = A
     Bobby: value = B
     ActiveObjectImpl displayString = Chris 1
     Alice: value = AA
     Bobby: value = BB
     ActiveObjectImpl displayString = Chris 2
     ActiveObjectImpl displayString = Chris 3
     Alice: value = AAA
     ActiveObjectImpl displayString = Chris 4
     Bobby: value = BBB
     Alice: value = AAAA
     ActiveObjectImpl displayString = Chris 5
     ActiveObjectImpl displayString = Chris 6
     Bobby: value = BBBB
     ActiveObjectImpl displayString = Chris 7
     ActiveObjectImpl displayString = Chris 8
     ActiveObjectImpl displayString = Chris 9
     Alice: value = AAAAA
     ActiveObjectImpl displayString = Chris 10
     Bobby: value = BBBBB
     ActiveObjectImpl displayString = Chris 11
     ActiveObjectImpl displayString = Chris 12
     ActiveObjectImpl displayString = Chris 13
     ActiveObjectImpl displayString = Chris 14
     Alice: value = AAAAAA
     ActiveObjectImpl displayString = Chris 15
     ActiveObjectImpl displayString = Chris 16
     Bobby: value = BBBBBB
     ActiveObjectImpl displayString = Chris 17
     ActiveObjectImpl displayString = Chris 18
     main: shutdown
     Chris:java.util.concurrent.RejectedExecutionException: Task designpattern.ActiveObject.Future.ActiveObjectImpl$1DisplayStringRequest@175e35fd rejected from java.util.concurrent.ThreadPoolExecutor@1999d966[Shutting down, pool size = 1, active threads = 1, queued tasks = 7, completed tasks = 33]
     ActiveObjectImpl displayString = Chris 19
     Alice: value = AAAAAAA
     Alice:java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.FutureTask@57003ff0 rejected from java.util.concurrent.ThreadPoolExecutor@1999d966[Shutting down, pool size = 1, active threads = 1, queued tasks = 6, completed tasks = 34]
     ActiveObjectImpl displayString = Chris 20
     ActiveObjectImpl displayString = Chris 21
     ActiveObjectImpl displayString = Chris 22
     Bobby: value = BBBBBBB
     */
}
