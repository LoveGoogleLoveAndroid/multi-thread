package basic.Eternity;

// 能量守恒定律：能量不会凭空创造，只会从一个地方转移到另一个地方
public class EnergySystem {
    private final double[] energyBoxes;
    private Object lock = new Object();

    public EnergySystem(int n, double initialEnergy) {
        energyBoxes = new double[n];

        for (int i = 0; i < energyBoxes.length; i++) {
            energyBoxes[i] = initialEnergy;
        }
    }

    public void transfer(int from, int to, double amount)
    {
        synchronized (lock) {
            /*
            if (energyBoxes[from] < amount) {
                return;
            }*/
            // while循环，保证条件不满足时人物都会被条件阻挡，而不是继续竞争CPU资源
            while (energyBoxes[from] < amount)
            {
                try {
                    // 条件不满足，将当前线程放入Wait Set
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName());
            energyBoxes[from] -= amount;
            System.out.printf("from  %d transfer %6.2f unit energy to  %d", from, amount, to);
            energyBoxes[to] += amount;
            System.out.printf(",  total energy: %10.2f %n", getTotalEnergy());

            // 唤醒所有在lock对象上等待的线程Wait Set，让他们去争取CPU资源以拿到锁
            lock.notifyAll();
            // 唤醒所有在lock对象上等待的某一个线程
            //lock.notify();
        }
    }

    public double getTotalEnergy()
    {
        double total = 0;

        for (double amount:energyBoxes)
        {
            total += amount;
        }

        return total;
    }

    public int getBoxAmount()
    {
        return energyBoxes.length;
    }
}
