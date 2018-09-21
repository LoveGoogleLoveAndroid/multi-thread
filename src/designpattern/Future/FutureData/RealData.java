package designpattern.Future.FutureData;

public class RealData implements Data {
    private final String content;

    public RealData(int count, char c) {
        System.out.println("    making RealData(" + count + ", " + c + ") begin.");
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = c;

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        this.content = new String(buffer);
        System.out.println("    making RealData(" + count + ", " + c + ") end.");
    }

    @Override
    public String getContent() {
        return content;
    }
}
