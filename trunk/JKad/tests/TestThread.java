public class TestThread extends Thread {
    private String name;
    
    public TestThread(String name)
    {
        this.name = name;
    }
    
    public void run()
    {
        for(int i = 0; i < 5; i++)
        {
            try {
                sleep((long)(1000 + (Math.random() * 1000)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[" + name + "] serial: " + SerialNum.get());
        }
    }
}
