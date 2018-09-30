package File.Serializable;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable{
    private String number;
    private String name;
    // 不使用JVM默认的序列化，可能是不做序列化，也可能自己实现序列化
    private transient int age;

    public Student() {
    }

    public Student(String number, String name, int age) {
        this.number = number;
        this.name = name;
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void writeObject(ObjectOutputStream s) throws IOException
    {
        // 把JVM能够默认序列化的元素进行序列化
        s.defaultWriteObject();
        // 自己完成age的序列化
        s.writeInt(age);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
    {
        // 把JVM能够默认反序列化的元素进行反序列化
        s.defaultReadObject();
        // 自己完成age的反序列化
        this.age = s.readInt();
    }

    @Override
    public String toString() {
        return "Student{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
