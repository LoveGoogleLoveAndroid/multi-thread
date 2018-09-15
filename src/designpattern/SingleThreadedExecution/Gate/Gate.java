package designpattern.SingleThreadedExecution.Gate;

public class Gate {
    private String name = "no-one";
    private String addres = "no-where";
    private int count = 0;

    public synchronized void pass(String name, String addres)
    {
        count++;
        this.name = name;
        this.addres = addres;
        check();
    }

    private void check()
    {
        if (name.charAt(0) != addres.charAt(0))
        {
            System.out.println("***************** broken ****************" + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "Gate{" +
                "name='" + name + '\'' +
                ", addres='" + addres + '\'' +
                ", count=" + count +
                '}';
    }
}
