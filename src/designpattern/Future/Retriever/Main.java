package designpattern.Future.Retriever;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Content content1 = Retriever.retrieve("http://www.yahoo.com/");
        Content content2 = Retriever.retrieve("http://www.baidu.com/");
        Content content3 = Retriever.retrieve("http://www.hao123.com/");

        saveToFile("yahoo.html", content1);
        saveToFile("baidu.html", content2);
        saveToFile("hao123.html", content3);

        long end = System.currentTimeMillis();
        System.out.println("Elapsed time = " + (end - start) + " mill seconds");
    }

    private static void saveToFile(String filename, Content content)
    {
        byte[] bytes = content.getBytes();

        try
        {
            System.out.println(Thread.currentThread().getName() + ": saving to " + filename);
            FileOutputStream out = new FileOutputStream(filename);

            for (int i = 0; i < bytes.length; i++) {
                out.write(bytes[i]);
            }
            out.close();
        }
        catch (IOException e)
        {

        }
        finally {

        }
    }
}
