package basic.Dynasty;

public class Stage extends Thread {
    @Override
    public void run() {
        ArmyRunnable armyTaskDynasty = new ArmyRunnable();
        ArmyRunnable armyTaskRevolt = new ArmyRunnable();
        Thread armyDynasty = new Thread(armyTaskDynasty, "basic/Dynasty");
        Thread armyRevolt = new Thread(armyTaskRevolt, "Revolt");
        System.out.println("the war is beginning, please watching.............................");
        // 王朝军队和起义军开始战争
        armyDynasty.start();
        armyRevolt.start();

        // 战争进行了10毫秒
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 英雄人物诞生
        System.out.println("the war continues, a here come up!");
        Thread hero = new Hero();
        hero.setName("chenyaojin");
        System.out.println("chenyaojin's dream is to end the war, make people happy!");

        // 让王朝军队和起义军的线程准备结束战争
        armyTaskDynasty.keepRunning = false;
        armyTaskRevolt.keepRunning =false;

        // 不应该用stop来停止线程，会使线程突然停止，依次循环都没有跑完
        // armyDynasty.stop();
        // armyRevolt.stop();

        // 等待起义军和王朝军队的线程结束
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 英雄人物出场
        hero.start();

        // 起义军和王朝军队的线程等待英雄人物结束战争
        try {
            hero.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Heroes make great contributions to our county, great honour for you!");
    }

    public static void main(String[] args) {
        new Stage().start();

        System.out.println("this is main thread.................................................");
    }
}
