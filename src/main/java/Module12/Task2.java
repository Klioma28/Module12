package Module12;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Task2 {
    public static void main(String[] args) throws InterruptedException {
        int n;
        do {
            System.out.println("Enter number for task 2(must be integer and greater than 1): ");
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();
        }while (n <= 1);

        MyProducer fizzProducer = new MyProducer() {
            int n;
            boolean updated = false;
            @Override
            public void setN(int n) {
                this.n = n;
                updated = true;
            }

            @Override
            public boolean isUpdated() {
                return updated;
            }


            @Override
            public void run() {
                while (true) {
                    try {
                        if(updated) {
                            updated = false;
                            if ((n % 3 == 0) && (n % 5 != 0)) {
                                System.out.print(", fizz");
                            }
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        MyProducer buzzProducer = new MyProducer() {
            int n;
            boolean updated = false;
            @Override
            public void setN(int n) {
                this.n = n;
                updated = true;
            }

            @Override
            public boolean isUpdated() {
                return updated;
            }


            @Override
            public void run() {
                while (true) {
                    try {
                        if(updated) {
                            updated = false;
                            if ((n % 3 != 0) && (n % 5 == 0)) {
                                System.out.print(", buzz");
                            }
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        MyProducer fizzbuzzProducer = new MyProducer() {
            int n;
            boolean updated = false;
            @Override
            public void setN(int n) {
                this.n = n;
                updated = true;
            }

            @Override
            public boolean isUpdated() {
                return updated;
            }


            @Override
            public void run() {
                while (true) {
                    try {
                        if(updated) {
                            updated = false;
                            if ((n % 3 == 0) && (n % 5 == 0)) {
                                System.out.print(", fizzbuzz");
                            }
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        MyProducer numberProducer = new MyProducer() {
            int n;
            boolean updated = false;
            @Override
            public void setN(int n) {
                this.n = n;
                updated = true;
            }

            @Override
            public boolean isUpdated() {
                return updated;
            }


            @Override
            public void run() {
                while (true) {
                    try {
                        if(updated) {
                            updated = false;
                            if ((n % 3 != 0) && (n % 5 != 0)) {
                                System.out.print(", " + n);
                            }
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        System.out.print("1");
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(numberProducer);
        executor.execute(fizzProducer);
        executor.execute(buzzProducer);
        executor.execute(fizzbuzzProducer);
        for (int i = 2; i <= n; i++) {
            fizzProducer.setN(i);
            buzzProducer.setN(i);
            fizzbuzzProducer.setN(i);
            numberProducer.setN(i);
            while (fizzProducer.isUpdated() || numberProducer.isUpdated()){
                Thread.sleep(100);
            }
        }
        System.exit(0);
    }
}
