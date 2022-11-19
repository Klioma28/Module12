package Module12;

public class Task1 {
    public static void main(String[] args) {

        new Thread(() -> {
            Thread.currentThread().setPriority(10);
            int a = 1;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(a + " seconds from program start");
                a++;
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Thread.currentThread().setPriority(1);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("5 seconds passed");
            }
        }).start();
    }
}
