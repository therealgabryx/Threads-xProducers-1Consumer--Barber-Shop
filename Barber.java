class Barber extends Thread {
  Semaphore mutexB; // mutexB: Mutex barber
  String name;

  public Barber(String name, Semaphore mB) {
    this.name = name;
    mutexB = mB;
  }

  public void run() {
    int wait, temp_remove, sleeping = 0;
    String customerID;
    
    System.out.print("\n[!] I am " + name + " the Barber! The shop is now open!");

    while (true) {
      wait = 300;

      mutexB.P();
      if (Main.empty == Main.DIM_BUFFER) {
        if (sleeping > 0) {
          System.out.print(".");
        } 
        else {
          System.out.print("\n?! I am the Barber! No clients for now, time to sleep a bit");
          sleeping++;
        }
      } 
      else {
        sleeping = 0;
        temp_remove = Main.remove;
        Main.remove = (Main.remove + 1) % Main.DIM_BUFFER;
        customerID = Main.Buffer[temp_remove];
        Main.Buffer[temp_remove] = "";
        System.out.print("\n- I am the Barber! Serving customer (" + customerID + ") from position [" + temp_remove + "]");
        Main.empty++;
      }
      mutexB.V();

      try {
        Thread.sleep(wait);
      } catch (Exception e) { }
    }
  }
}