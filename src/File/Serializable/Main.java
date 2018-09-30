package File.Serializable;

import java.io.*;

public class Main {

    private static void objectOutputStreamTest(final String fileName) throws IOException, ClassNotFoundException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        Student student = new Student("94", "小何峰", 20);
        oos.writeObject(student);
        oos.flush();
        oos.close();
    }

    private static void objectInputStreamTest(final String fileName) throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Student student1 = (Student)ois.readObject();
        System.out.println(student1);
        ois.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final String FILE_NAME = "./src/File/Serializable/student.txt";
        objectOutputStreamTest(FILE_NAME);
        objectInputStreamTest(FILE_NAME);
    }
}
