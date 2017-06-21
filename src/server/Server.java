package server;

import entities.Player;

import java.net.DatagramSocket;
import java.util.HashMap;

/**
 * Created by Owner on 3/22/2017.
 */
public class Server
{
    HashMap<String, Player> SERVER_PLAYERS = new HashMap<>();
    DatagramSocket socket;
    public Server()
    {
    }
    public void receive()
    {
        /*byte[] packetData = new byte[1024];
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length);
        try
        {
            socket.receive(packet);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        if(packet.getData() != null)
        {
            String e = new String(packetData);
        }*/
    }
    public static Player getPlayer(String name)
    {
        return null; //////
    }
}
