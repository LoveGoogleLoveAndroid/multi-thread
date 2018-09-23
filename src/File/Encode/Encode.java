package File.Encode;

import java.io.UnsupportedEncodingException;

public class Encode
{
    public static void main(String[] args) throws UnsupportedEncodingException{
        String s = "hi小何峰";
        // 文件本身是字节序列，可以存放任意编码类型的字节序列，但是如果在中文机器上创建文本文件，可能会默认设置为只认识ANSI编码
        // 转换为字节序列用的是项目默认的编码方式utf-8
        byte[] bytes1 = s.getBytes();
        for(byte b:bytes1)
        {
            // 把字节转为int，以16进制方式显示，即前面24位加0，再取后8位
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        // utf-8中文占二个字节，英文占一个字节
        byte[] bytes2 = s.getBytes("gbk");
        for(byte b:bytes2)
        {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        // utf-8中文占三个字节，英文占一个字节
        byte[] bytes3 = s.getBytes("utf-8");
        for(byte b:bytes3)
        {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        // utf-16be中文和英文都是占两个字节
        byte[] bytes4 = s.getBytes("utf-16be");
        for(byte b:bytes4)
        {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();
    }
}
