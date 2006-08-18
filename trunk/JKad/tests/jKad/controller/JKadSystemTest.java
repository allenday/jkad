package jKad.controller;

import java.net.DatagramSocket;

import junit.framework.TestCase;

public class JKadSystemTest extends TestCase {

    /*
     * Test method for 'jKad.controller.JKadSystem.getSocket()'
     */
    public void testGetSocket() {
        DatagramSocket instance1 = JKadSystem.getSocket();
        DatagramSocket instance2 = JKadSystem.getSocket();
        assertSame(instance1, instance2);
        Thread thread1 = new Thread(new JKadSystem("System 1"));
        Thread thread2 = new Thread(new JKadSystem("System 2"));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}