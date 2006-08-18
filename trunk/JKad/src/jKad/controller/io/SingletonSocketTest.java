package jKad.controller.io;

import java.net.DatagramSocket;

import junit.framework.TestCase;

public class SingletonSocketTest extends TestCase {

    /*
     * Test method for 'jKad.controller.io.SingletonSocket.getInstance()'
     */
    public void testGetInstance() {
        DatagramSocket instance1 = SingletonSocket.getInstance();
        DatagramSocket instance2 = SingletonSocket.getInstance();
        assertSame(instance1, instance2);
        SingletonSocketTestThread thread1 = new SingletonSocketTestThread("thread 1");
        SingletonSocketTestThread thread2 = new SingletonSocketTestThread("thread 2");
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotSame(thread1.getSocket(), thread2.getSocket());
    }

}
class SingletonSocketTestThread extends Thread {
    
    private DatagramSocket socket;
    
    public SingletonSocketTestThread(String name) {
        super(name);
    }

    public void run() 
    {
        socket = SingletonSocket.getInstance();
    }
    
    public DatagramSocket getSocket()
    {
        return socket;
    }
}
