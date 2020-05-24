import java.util.Random;

class Customer extends Thread {
  Semaphore mutexC; // mutexC: Mutex betw. customers
  String name;

  public Customer(String name, Semaphore mC) {
    this.name = name;
    mutexC = mC;
  }

  public void run() {
    int temp_add;

    mutexC.P();
    if (Main.empty == 0) {
      System.out.print("\n?! Customer (" + name + "): too many people here, imma head out then");
    } else {
      temp_add = Main.add;
      Main.add = (Main.add + 1) % Main.DIM_BUFFER;
      Main.Buffer[temp_add] = name;
      System.out.print("\n + Customer (" + name + ") in queue at position [" + temp_add + "]");
      Main.empty--;
    }
    mutexC.V();
  }
}