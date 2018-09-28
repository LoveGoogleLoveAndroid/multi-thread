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

    // 单字节带缓冲的拷贝  1M file copy operation -> 3 milli-seconds
    private static void copyFile(final File src, final File dest) throws IOException
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
            out.flush();    // recommend
        }

        in.close();
        out.close();
    }

    // 单字节不带缓冲的拷贝   1M file copy operation -> 2075 milli-seconds
    private static void copyFileByByte(final File src, final File dest) throws IOException
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
        int c;
        while ((c = in.read()) != -1)
        {
            out.write(c);
            out.flush();    // recommend
        }

        in.close();
        out.close();
    }

    // 带缓冲的拷贝    1M file copy operation -> 1513 milli-seconds
    private static void copyFileByBuffer(final File src, final File dest) throws IOException
    {
        if (!src.exists())
        {
            throw new IllegalArgumentException("file " + src + " does not exist.");
        }

        if (!src.isFile())
        {
            throw new IllegalArgumentException(src + " is not a file.");
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
        int c;
        while ((c = bis.read()) != -1)
        {
            bos.write(c);
            bos.flush();    // must
        }
        bis.close();
        bos.close();
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

    private static void inputStreamReaderTest(final String fileName) throws IOException
    {
        FileInputStream in = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        FileOutputStream out = new FileOutputStream(fileName + "1");
        OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
        int c;
        /*
        while ((c = isr.read()) != -1)
        {
            System.out.print((char)c);
        }*/
        char[] buffer = new char[1024];
        while ((c = isr.read(buffer, 0, buffer.length)) != -1)
        {
            String s = new String(buffer, 0, c);
            System.out.print(s);
            osw.write(buffer, 0, c);
            osw.flush();
        }

        in.close();
        isr.close();
    }

    private static void fileReaderTest(final String fileName) throws IOException
    {
        FileReader fr = new FileReader(fileName);
        FileWriter fw = new FileWriter(fileName + "1", false);  // append
        char[] buffer = new char[1024];
        int c;
        while ((c = fr.read(buffer, 0, buffer.length)) != -1)
        {
            System.out.print(buffer);
            fw.write(buffer, 0, c);
            fw.flush();
        }

        fr.close();
        fw.close();
    }


    public static void main(String[] args) throws IOException{
        //final String FILE_PATH = "/Users/sky/work/java/MultiThread/README.md";
        //printHex(FILE_PATH);
        //printHexByByteArray(FILE_PATH);

        //final String FILE_OUTPUT = "/Users/sky/work/java/MultiThread/output.txt";
        //fileOutStreamTest(FILE_OUTPUT);

        /*final String FILE_OUTPUT_SRC = "/Users/sky/work/java/MultiThread/marry_src.swf";
        final String FILE_OUTPUT_DES = "/Users/sky/work/java/MultiThread/marry_dest.swf";
        long start = System.currentTimeMillis();
        copyFileByByte(new File(FILE_OUTPUT_SRC), new File(FILE_OUTPUT_DES));
        long end = System.currentTimeMillis();
        System.out.println("duration of copying file: " + (end - start) + " milli seconds");*/

        //final String FILE_NAME = "/Users/sky/work/java/MultiThread/dos.dat";
        //dataOutStreamTest(FILE_NAME);
        //dataInputStreamTest(FILE_NAME);

        //final  String FILE_NAME = "./src/File/README.md";
        //inputStreamReaderTest(FILE_NAME);

        final  String FILE_NAME = "/Users/sky/work/java/MultiThread/output.txt";
        fileReaderTest(FILE_NAME);
    }
}
