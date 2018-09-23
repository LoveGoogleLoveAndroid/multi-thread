package File.Files;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccess {

    private static void RandomAccessFile() throws IOException
    {
        File randomTest = new File("File");

        if (!randomTest.exists())
        {
            randomTest.mkdir();
        }

        File file = new File(randomTest, "RandomTest.txt");

        if (!file.exists())
        {
            file.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        // 当前指针到文件开始的字节长度
        System.out.println("raf.point = " + raf.getFilePointer());

        raf.write('A');
        raf.write('B');
        System.out.println("raf.point = " + raf.getFilePointer());

        int i = 0x7fffffff;
        /*raf.write(i >>> 24);    // 取高8位
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i >>> 0);
        */
        raf.writeInt(i);
        System.out.println("raf.point = " + raf.getFilePointer());

        String s = "小何峰";
        byte[] gbk = s.getBytes("gbk");
        raf.write(gbk);
        System.out.println("raf.point = " + raf.getFilePointer());

        // 读文件，必须把指针移动头部
        raf.seek(0);
        byte[] buf = new byte[(int)raf.length()];
        raf.read(buf);
        System.out.println(Arrays.toString(buf));

        for (byte b:buf)
        {
            System.out.print(Integer.toHexString(b & 0xFF) + " ");
        }

        raf.close();
    }

    public static void main(String[] args) throws IOException{
        RandomAccessFile();
    }

    /*
    *
    *   raf.point = 0
        raf.point = 2
        raf.point = 6
        raf.point = 12
        [65, 66, 127, -1, -1, -1, -48, -95, -70, -50, -73, -27]
        41 42 7f ff ff ff d0 a1 ba ce b7 e5
*/
}
