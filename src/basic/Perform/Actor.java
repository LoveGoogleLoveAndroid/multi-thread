package basic.Perform;

public class Actor extends Thread{

    @Override
    public void run() {
        System.out.println(getName() + " is a actor!");

        int count = 0;
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println(getName() + " is performing " + (++count));

            if (count == 100)
            {
                keepRunning = false;
            }

            if (count % 10 == 0)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(getName() + "'s show finished!");
    }
}
