package jKad.controller.io;

import jKad.controller.threads.CyclicThread;
import jKad.structures.buffers.DatagramBuffer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;

public class UDPSender extends CyclicThread
{
    private static Logger logger = Logger.getLogger(UDPSender.class);
    
    private DatagramSocket socket;
    private DatagramBuffer buffer;

    private long sentPackets;
    
    public UDPSender() throws SocketException
    {
        this(SingletonSocket.getInstance(), DatagramBuffer.getSentBuffer());
    }
    
    public UDPSender(DatagramSocket socket, DatagramBuffer buffer) throws SocketException
    {
        super(Thread.currentThread().getThreadGroup().getName() + "." + UDPSender.class.getSimpleName());
        if(socket == null)
            throw new NullPointerException("socket is null!");
        if(buffer == null)
            throw new NullPointerException("buffer is null!");
        this.socket = socket;
        this.buffer = buffer;
        this.sentPackets = 0L;
    }

    protected void cycleOperation()
    {
        if (!socket.isClosed())
        {
            try
            {
                while (!buffer.isEmpty())
                    socket.send(buffer.remove());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    protected void finalize()
    {
        if (socket != null)
            socket.close();
    }
    
    public long getSentPacketsAmount()
    {
        return this.sentPackets;
    }

    @Override
    protected Logger getLogger()
    {
        return logger;
    }
}
