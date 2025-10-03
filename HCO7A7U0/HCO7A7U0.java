package HCO7A7U0;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HCO7A7U0 {
    public static void main(String[] args) throws Exception {
        String host  = "203.162.10.109";
        int port = 2207;
        InetAddress inetAddress = InetAddress.getByName(host);

        DatagramSocket socket = new DatagramSocket();
        String sendA = ";B22DCCN470;HCO7A7U0";
       
        DatagramPacket dpA = new DatagramPacket(sendA.getBytes(), sendA.length(), inetAddress, port);
        socket.send(dpA);
        System.out.println("Send A:" + sendA);

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);

        socket.receive(dpB);
        

        String lineB = new String(dpB.getData(), 0 , dpB.getLength());
        String []listB =  lineB.split(";");
        BigInteger b1 = new BigInteger(listB[1].trim());
        BigInteger b2 = new BigInteger(listB[2].trim());

        BigInteger tong = b1.add(b2);
        BigInteger hieu = b1.subtract(b2);

        String keyB = listB[0] + ";" + tong.toString() + "," + hieu.toString();

        DatagramPacket dpC = new DatagramPacket(keyB.getBytes(), keyB.length(), inetAddress, port);
        socket.send(dpC);
        System.out.println("KeyC: "+keyB);

        socket.close();
        System.out.println("Close");
       

    }
}
