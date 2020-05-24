class Main {
  static final int DIM_BUFFER = 6;
  protected static String[] Buffer = new String[DIM_BUFFER];
  protected static int add = 0;
  protected static int remove = 0;
  protected static int empty = DIM_BUFFER;

  public static void main(String[] args) {
    Semaphore mutexC = new Semaphore(1);
    Semaphore mutexB = new Semaphore(1);  

    Barber barber = new Barber("Jay", mutexB);
    barber.start(); 

    int id = 1;
    while (true) {
      Thread customer = new Thread(new Customer("C#" + Integer.toString(id), mutexC));
      customer.start();
      id++;

      try {
        // Thread.sleep((int) (Math.random() * (2000 - 10)) + 10);
        Thread.sleep(150);
      } catch (InterruptedException e) { e.printStackTrace(); }
    }
  }
}