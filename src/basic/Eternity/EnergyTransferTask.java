package basic.Eternity;

public class EnergyTransferTask implements Runnable {

    // 共享的能量世界
    private EnergySystem energySystem;
    // 能量转移的盒子下标
    private int fromBox;
    // 一次最大转移单元
    private double maxAmount;
    // 最大休眠时间
    private int DELAY = 10;

    public EnergyTransferTask(EnergySystem energySystem, int fromBox, double maxAmount) {
        this.energySystem = energySystem;
        this.fromBox = fromBox;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        try {
            while (true)
            {
                int toBox = (int)(energySystem.getBoxAmount() * Math.random());
                double amount = maxAmount * Math.random();
                energySystem.transfer(fromBox, toBox, amount);
                Thread.sleep((int)(DELAY * Math.random()));
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
