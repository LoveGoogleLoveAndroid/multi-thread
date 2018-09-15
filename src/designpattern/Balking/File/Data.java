package designpattern.Balking.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Data {
    private final String filename;
    private String content;
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent)
    {
        this.content = newContent;
        changed = true;
    }

    // SaverThread和ChangerThread都有可能调用，但同时只能被一个调用
    public synchronized void save() throws IOException
    {
        if (!changed)
        {
            // 调用此处说明内容未修改，该线程不需执行
            System.out.println(Thread.currentThread().getName() + ", no need to save, return now!");
            return;
        }

        doSave();
        changed = false;
    }

    private void doSave() throws IOException
    {
        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);

        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
}
