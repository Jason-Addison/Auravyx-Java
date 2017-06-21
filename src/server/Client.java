package server;

import engine.game.state.menustate.MenuState;
import tools.Utilities;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Owner on 3/22/2017.
 */
public class Client
{

    public static DatagramChannel channel;
    InetSocketAddress address;
    public static SocketAddress localAddress;
    int port = 3456;
    public static String IP = "";
    public static String HOST_NAME = "";

    public Client()
    {
        try
        {//192.160.1.2
            address = new InetSocketAddress("192.168.1.4", port);
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 0));
            channel.connect(new InetSocketAddress(InetAddress.getByName("192.168.1.4"), port));
            channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        }
        catch(IOException s)
        {
            s.printStackTrace();
        }

    }

    public void update()
    {
        //receive();
        updateClientInfo();
    }

    private void updateClientInfo()
    {
        try
        {
            HOST_NAME = InetAddress.getLocalHost().getHostName();
            IP = InetAddress.getLocalHost().getHostAddress();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void receive()
    {
        while(true)
        {
            byte[] packetData = new byte[1024];
            ByteBuffer buf = ByteBuffer.wrap(packetData);
            SocketAddress sa = null;
            try
            {
                sa = channel.receive(buf);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            if(sa != null)
            {
                InetSocketAddress ad = (InetSocketAddress) (sa);
                byte[] yea = buf.array();
                yea = Arrays.copyOfRange(yea, 4, yea.length);
                String str = new String(yea);
                System.out.println(str);
                MenuState.SERVER_TESTER = str;
            }
            else
            {
                break;
            }
            break;

        }
    }

    public void send()
    {
        byte[] id = Utilities.shortToBytes((short) 1);
        Random ran = new Random();
        String msg = "the_worst_player_ever";//"" + UUID.randomUUID();//ran.nextInt();
        byte[] length = Utilities.shortToBytes((short) msg.getBytes().length);
        byte[] init = new byte[4 + msg.getBytes().length];
        init[0] = id[0]; init[1] = id[1]; init[2] = length[0]; init[3] = length[1];
        init = Utilities.combineArray(init, msg.getBytes(), 4);
        ByteBuffer buf = ByteBuffer.wrap(init, 0, init.length);
        //DatagramPacket packet = new DatagramPacket(init, init.length, address, port);
       // DatagramPacket packet = new DatagramPacket(packetData.getBytes(), packetData.length(), address, port);
        try
        {
            System.out.println(((InetSocketAddress) channel.getLocalAddress()));
            channel.send(buf, address);
           // socket.send(packet);
            System.out.println("sent to : " + address.getAddress().getHostAddress());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
