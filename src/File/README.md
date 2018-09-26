

## 字节流
1. InputStream、OutputStream抽象了应用程序读写数据的方式
2. EOF = End，读到-1即文件结尾
3. 输入流基本方法：
```
int b = in.read()； // 读到一个字节无符号填充到Int低8位
in.read(byte[] buf); 
in.read(byte[] buf, int start, int size);
```
4. 输出流基本方法：
```
out.write(int b)； // 写入一个Int的低8位，即byte到输出流
out.write(byte[] buf); 
out.write(byte[] buf, int start, int size);
```
5. FileInputStream、FileOutputStream实现了在文件上读写数据
6. DataInputStream、DataOutputStream是对FileInputStream、FileOutputStream流的扩展
可以更加方便的读写int、long、double等数据类型

## 字符流