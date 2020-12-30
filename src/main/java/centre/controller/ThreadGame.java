package centre.controller;

import manager.EnvironmentVariable;

public class ThreadGame extends Thread {
    public final int TIME_PROCESS = EnvironmentVariable.DELAYPROCESS;
    private MainController mainController;

    public ThreadGame(MainController mainController) {
        this.mainController = mainController;
    }

    private long maxTime = 0;

    @Override
    public void run() {
        mainController.update();
        long timeStart = System.currentTimeMillis();
        long timeLate = System.currentTimeMillis();
        int fps = 0;

        while (true) {
            long start = System.currentTimeMillis();
            mainController.draw();
            mainController.update();
            mainController.render();

            mainController.timeGame = (int)(System.currentTimeMillis()-timeStart)/1000;
            long end = System.currentTimeMillis();
            long timeProcess = end - start;
            fps++;

//            if (maxTime < timeProcess)
//                maxTime = timeProcess;

            if (System.currentTimeMillis() - timeLate > 1000){
                System.out.println("fps = " + fps );
                fps = 0;
                timeLate = System.currentTimeMillis();
            }
            try {
                if (TIME_PROCESS - timeProcess > 0)
                    sleep(TIME_PROCESS - timeProcess);
//                else
//                    System.out.println("time process = " + timeProcess + " max time = " + maxTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
