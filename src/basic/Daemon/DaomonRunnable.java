package basic.Daemon;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DaomonRunnable implements Runnable {

    @Override
    public void run() {

        System.out.println("enter daemon thread " + Thread.currentThread().getName());

        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("exist daemon thread " + Thread.currentThread().getName());

    }

    private void writeToFile() throws IOException, InterruptedException
    {
        File file = new File("daomen_thread.txt");
        OutputStream os = new FileOutputStream(file, true);
        int count = 0;
        System.out.println("file.getAbsolutePath: " + file.getAbsolutePath());
        System.out.println("file.getCanonicalPath: " + file.getCanonicalPath());

        while (count < 999)
        {
            os.write(("hello world " + count + "\n").getBytes());
            System.out.println("daemon thread " + Thread.currentThread().getName() + " write " + count++);
            Thread.sleep(1000);
        }
    }
}