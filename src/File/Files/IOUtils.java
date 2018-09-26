package File.Files;

import java.io.*;

public class IOUtils {
    private static void printHex(final String fileName) throws IOException
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

    private static void printHexByByteArray(final String fileName) throws IOException
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

    private static void fileOutStreamTest(final String fileName) throws IOException
    {
        File file = new File(fileName);
        if (!file.exists())
        {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        //char ch = 'A';
        //System.out.println("A.length = " + );
        out.write('A'); // 写入A的低8位
        out.write('B');

        int a = 10; //  write 只能写8位，int 4个字节需要写4次
        out.write(a >>> 24);
        out.write(a >>> 16);
        out.write(a >>> 8);
        out.write(a >>> 0);

        byte[] china = "中国".getBytes("gbk");
        out.write(china);
        out.close();

        printHex(fileName);
    }

    public static void copyFile(final File src, final File dest) throws IOException
    {
        if (!src.exists())
        {
            throw new IllegalArgumentException("file " + src + " does not exist.");
        }

        if (!src.isFile())
        {
            throw new IllegalArgumentException(src + " is not a file.");
        }

        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dest);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf, 0, buf.length)) != -1)
        {
            out.write(buf, 0, len);
            out.flush();
        }

        in.close();
        out.close();
    }

    private static void dataOutStreamTest(final String fileName) throws IOException
    {
        File file = new File(fileName);
        if (!file.exists())
        {
            file.createNewFile();
        }

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        dos.writeInt(10);
        dos.writeInt(-10);

        dos.writeLong(20L);
        dos.writeLong(-20L);

        dos.writeDouble(30.11);
        dos.writeDouble(-30.11);

        dos.writeUTF("中国");
        dos.writeUTF("I love U");

        dos.writeChars("我爱你");
        dos.writeChars("I like U");

        // 添加一个结束符
        dos.writeChar('\0');

        dos.close();
    }

    private static void dataInputStreamTest(final String fileName) throws IOException
    {
        DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
        int i = dis.readInt();
        System.out.println(i);
        i = dis.readInt();
        System.out.println(i);

        long l = dis.readLong();
        System.out.println(l);
        l = dis.readLong();
        System.out.println(l);

        double d = dis.readDouble();
        System.out.println(d);
        d = dis.readDouble();
        System.out.println(d);

        String utf8 = dis.readUTF();
        System.out.println(utf8);
        utf8 = dis.readUTF();
        System.out.println(utf8);

        char chars;
        while ( (chars = dis.readChar()) != '\0' ){
            System.out.print(chars);
        }

        dis.close();
    }


    public static void main(String[] args) throws IOException{
        //final String FILE_PATH = "/Users/sky/work/java/MultiThread/README.md";
        //printHex(FILE_PATH);
        //printHexByByteArray(FILE_PATH);

        //final String FILE_OUTPUT = "/Users/sky/work/java/MultiThread/output.txt";
        //fileOutStreamTest(FILE_OUTPUT);

        //final String FILE_OUTPUT_SRC = "/Users/sky/work/java/MultiThread/output.txt";
        //final String FILE_OUTPUT_DES = "/Users/sky/work/java/MultiThread/output_des.txt";
        //copyFile(new File(FILE_OUTPUT_SRC), new File(FILE_OUTPUT_DES));
        final String FILE_NAME = "/Users/sky/work/java/MultiThread/dos.dat";
        dataOutStreamTest(FILE_NAME);
        dataInputStreamTest(FILE_NAME);
    }
}
