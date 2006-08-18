package jKad.controller.io;

import jKad.controller.ThreadGroupLocal;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SingletonSocket
{
    private static Integer actualPort = null;

    private static ThreadGroupLocal<DatagramSocket> tlSocket = new ThreadGroupLocal<DatagramSocket>()
    {
        public DatagramSocket initialValue()
        {
            DatagramSocket socket = null;
            try
            {
                if(actualPort == null)
                {
                    String startPort = System.getProperty("jkad.socket.startPort");
                    actualPort = Integer.parseInt(startPort);
                }
                socket = new DatagramSocket(actualPort, InetAddress.getLocalHost());
            } catch (UnknownHostException e)
            {
                e.printStackTrace();
            } catch (SocketException e)
            {
                e.printStackTrace();
            }
            actualPort++;
            return socket;
        }
    };

    protected SingletonSocket()
    {
    }

    public static DatagramSocket getInstance()
    {
        return tlSocket.get();
    }
}