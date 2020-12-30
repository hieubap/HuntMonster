import com.aparapi.Kernel;
import com.aparapi.device.Device;
import com.aparapi.internal.kernel.KernelManager;
import com.aparapi.internal.kernel.KernelPreferences;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MainClass {
    public static void testGPU() {
        KernelPreferences preferences = KernelManager.instance().getDefaultPreferences();
        System.out.println("-- Devices in preferred order --");
        for (Device device : preferences.getPreferredDevices(null)) {
            System.out.println("----------");
            System.out.println(device);
        }

        final int size = 100000;
        final int[] a = IntStream.range(2, size + 2).toArray();
        final boolean[] primeNumbers = new boolean[size];

        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int gid = getGlobalId();
                int num = a[gid];
                boolean prime = true;
                for (int i = 2; i < num; i++) {
                    if (num % i == 0) {
                        prime = false;
                        //break is not supported
                    }
                }
                primeNumbers[gid] = prime;
            }
        };
        long startTime = System.currentTimeMillis();
        kernel.execute(size);
        System.out.printf("time taken: %s ms%n", System.currentTimeMillis() - startTime);
        System.out.println(Arrays.toString(Arrays.copyOf(primeNumbers, 20)));//just print a sub array
        kernel.dispose();
    }

    public static void testThread() {
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("run action 1 " + System.currentTimeMillis());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("run action 2 " + System.currentTimeMillis());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread a = new Thread(run1);
        a.start();
        Thread b = new Thread(run2);
        b.start();

    }

    public static void main(String[] args) {
        testGPU();
//        testThread();
//        System.out.println("end main");
    }
}
