package File.Files;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void listDirectory(File dir) throws IOException
    {
        if (!dir.exists())
        {
            throw new IllegalArgumentException("directory: " + dir + " does not existed.");
        }

        if (!dir.isDirectory())
        {
            throw new IllegalArgumentException("directory: " + dir + " is not a directory.");
        }

        // 返回的是字符串数组，不包含子目录
        String[] filenames = dir.list();
        System.out.println("filenames-------------------------------------------");

        for (String string:filenames)
        {
            System.out.println(string);
        }

        File[] files = dir.listFiles();
        System.out.println("files-----------------------------------------------");

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory())
                {
                    listDirectory(file);
                }
                else
                {
                    System.out.println(file);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final String DIR_PATH = "/Users/sky/work/java/MultiThread";
        listDirectory(new File(DIR_PATH));
    }
}
