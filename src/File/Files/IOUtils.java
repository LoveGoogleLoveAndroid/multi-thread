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

    public static void main(String[] args) throws IOException{
        final String FILE_PATH = "/Users/sky/work/java/MultiThread/README.md";
        printHex(FILE_PATH);
    }
}
