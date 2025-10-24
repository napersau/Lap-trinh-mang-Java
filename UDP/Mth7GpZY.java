package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class Mth7GpZY {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2207;
        InetAddress inetAddress = InetAddress.getByName(host);
        DatagramSocket socket = new DatagramSocket();

        String strA = ";B22DCCN470;Mth7GpZY";
        DatagramPacket dpA = new DatagramPacket(strA.getBytes(), strA.length(), inetAddress, port);

        socket.send(dpA);

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        String strB = new String(buffer);
        System.out.println("Str: " + strB);
        String strList[] = strB.trim().split(";");
        String chuList[] = strList[1].trim().split(",");

        int so[] = new int[chuList.length];
        for(int i = 0; i < chuList.length; i++){
            so[i] = Integer.parseInt(chuList[i]);
        } 
        Arrays.sort(so);

        String key = strList[0] + ";" + (so[so.length - 1] + "")+ "," + (so[0] + "");

        DatagramPacket dpC = new DatagramPacket(key.getBytes(), key.length(), inetAddress, port);
        socket.send(dpC);

        socket.close();
        
     }
}
