package File.Files;

import java.io.FileInputStream;
import java.io.IOException;

public class IOUtils {
    private static void printHex(String fileName) throws IOException
    {
        FileInputStream in = new FileInputStream(fileName);
        int b;
        int i = 1;

        while ((b = in.read()) != -1)
        {
            if (b <= 0xF)
            {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(b) + "  ");

            if ( i++ % 10 == 0)
            {
                System.out.println();
            }
        }

        in.close();
    }

    private static void printHexByByteArray(String fileName) throws IOException
    {
        FileInputStream in = new FileInputStream(fileName);
        byte[] buf = new byte[1024];

        int bytes = 0;
        int j = 1;

        while ((bytes = in.read(buf, 0, buf.length)) != -1)
        {
            for (int i = 0; i < bytes; i++) {
                if ((buf[i] & 0xFF) <= 0xF)
                {
                    System.out.print("0");
                }
                System.out.print(Integer.toHexString(buf[i] & 0xFF) + "  ");

                if (j++ % 10 == 0)
                {
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        final String FILE_PATH = "/Users/sky/work/java/MultiThread/README.md";
        //printHex(FILE_PATH);
        printHexByByteArray(FILE_PATH);
    }
}
