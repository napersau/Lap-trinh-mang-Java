package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class G2339v {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.162";
        int port = 2208;

        InetAddress inetAddress = InetAddress.getByName(host);
        DatagramSocket socket = new DatagramSocket();

        String code = ";B22DCCN470;54G2339v";
        DatagramPacket dpA = new DatagramPacket(code.getBytes(), code.length(), inetAddress, port);

        socket.send(dpA);

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        String s = new String(dpB.getData());
        String sList[] = s.trim().split(";");

        String 




    }
}
