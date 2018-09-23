package designpattern.ActiveObject.Future;

public class ActiveObjectFactory {
    public static ActiveObject createActiveObject()
    {
        return new ActiveObjectImpl();
    }
}
