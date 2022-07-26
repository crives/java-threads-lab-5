import java.sql.SQLOutput;
import java.util.stream.IntStream;

public class Main {
    /*
    You are given a class called SavingsAccount. It has:

    A long field called total.
    A deposit method that adds to the total.
    A withdraw method that subtracts the given amount from the total.
    It returns true if the operation is valid and successful and false otherwise.
    The class should work with multiple threads. If multiple threads are operating
    on the same instance of SavingsAccount, the total must have the correct value
    once all the thread operations are completed.
     */
    public static void main(String[] args) throws InterruptedException {
        SavingsAccount savings = new SavingsAccount();

        Thread t0 = new Thread(() -> {
            String curThreadName = Thread.currentThread().getName();
            savings.deposit(100);
            System.out.println(curThreadName + " is depositing $100 to the total equalling $" + savings.getTotal());
        });

        Thread t1 = new Thread(() -> {
            String curThreadName = Thread.currentThread().getName();
            savings.deposit(200);
            System.out.println(curThreadName + " is depositing $200 to the total equalling $" + savings.getTotal());
        });

        Thread t2 = new Thread(() -> {
            String curThreadName = Thread.currentThread().getName();
            savings.withdraw(100);
            System.out.println(curThreadName + " is withdrawing $100 from the total equalling $"
                    + savings.getTotal()
                    + "\nWithdraw is " + savings.withdraw(100));
        });

        Thread t3 = new Thread(() -> {
            String curThreadName = Thread.currentThread().getName();
            savings.withdraw(200);
            System.out.println(curThreadName + " is withdrawing $200 from the total equalling $"
                    + savings.getTotal()
                    + "\nWithdraw is " + savings.withdraw(200));
        });

        t0.start();
        t1.start();
        t2.start();
        t3.start();

        t0.join();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(savings.getTotal());
    }
}
