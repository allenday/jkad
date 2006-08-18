package jKad.controller.io;

import jKad.controller.threads.CyclicThread;
import jKad.structures.buffers.DatagramBuffer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;

public class UDPReceiver extends CyclicThread
{
    private static Logger logger = Logger.getLogger(UDPReceiver.class);
    
    private DatagramSocket socket;
    private DatagramBuffer buffer;
    
    private long receivedPackets;

    public UDPReceiver() throws SocketException
    {
        this(SingletonSocket.getInstance(), DatagramBuffer.getReceivedBuffer());
    }

    public UDPReceiver(DatagramSocket socket, DatagramBuffer buffer) throws SocketException
    {
        super(Thread.currentThread().getThreadGroup().getName() + "." + UDPReceiver.class.getSimpleName());
        if (socket == null)
            throw new NullPointerException("socket is null");
        if (buffer == null)
            throw new NullPointerException("buffer is null");
        this.socket = socket;
        this.buffer = buffer;
        
        this.receivedPackets = 0L;
    }

    protected void cycleOperation()
    {
        DatagramPacket packet = new DatagramPacket(new byte[256], 256);
        try
        {
            socket.receive(packet);
            buffer.add(packet);
            receivedPackets ++;
        } catch (SocketException e)
        {
            logger.error(e);
        } catch (IOException e)
        {
            logger.error(e);
        }
    }

    public void stopThread()
    {
        this.interrupt();
        super.stopThread();
    }
    
    protected void finalize()
    {
        if (socket != null)
            socket.close();
    }
    
    public long getReceivedPacketsAmount()
    {
        return receivedPackets;
    }

    protected Logger getLogger()
    {
        return logger;
    }
}
