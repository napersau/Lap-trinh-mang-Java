package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class tmp {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2208;

        DatagramSocket socket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName(host);

        String sendA = ";B22DCCN470;JaY5lCcc";
        DatagramPacket dpA = new DatagramPacket(sendA.getBytes(), sendA.length(), inetAddress, port);
        socket.send(dpA);

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        String line = new String(dpB.getData());
        String []lineList = line.trim().split(";");
        String []lineS = lineList[1].split("\\s+");
        for(int i = 0; i < lineS.length; i++){
            lineS[i] = lineS[i].substring(0,1).toUpperCase() + lineS[i].substring(1).toLowerCase();
        }
        String key = lineList[0] + ";" + String.join(" ", lineS);

        DatagramPacket dpc = new DatagramPacket(key.getBytes(), key.length(), inetAddress, port);
        socket.send(dpc);

        socket.close();

        
    }
}
