

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
7. BufferedInputStream、BufferedOutputStream提供了带缓冲区的读写操作，会提高IO性能
## 字符流
1. 编码
2. Java中的文本char是16位无符号整数，是字符的UNICODE编码（双字节编码）
文件是byte的数据序列，文本文件是文本char序列按照某种编码（UTF-8, UTF-16BE, GBK）序列化位byte
的存储
3. 字符流Reader、Write是一次处理一个字符，底层仍然是基本的字节序列，操作的都是文本文件
InputStreamReader完成byte流解析为char流，按照编码解析
OutputStreamWriter完成char流到byte流，按照编码解析
4. FileReader和FileWriter是用来读取/写入字符文件的便捷类，但是无法指定编码类型  
FileReader: 使用带有指定文件的String参数的构造方法。创建该输入流对象，并关联源文件  
FileWriter: 创建字符输出流类对象和已存在的文件相关联。文件不存在的话，并创建
5. 字符流的过滤器
BufferedReader: 一次读一行  
BufferedWriter/PrintWriter: 一次写一行

