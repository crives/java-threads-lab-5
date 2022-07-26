public class SavingsAccount {

  private volatile long total = 0;

  public synchronized boolean withdraw(long amount) {
      if (total - amount < 0) {
        return false;
      } else {
        total -= amount;
        return true;
      }
  }

  public synchronized void deposit(long amount) {
    total += amount;
  }

  public synchronized long getTotal() {
      return total;
  }
}