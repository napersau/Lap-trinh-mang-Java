package oQFCLzJs;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.xml.crypto.Data;

public class oQFCLzJs {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2207;

        InetAddress inetAddress = InetAddress.getByName(host);
        DatagramSocket socket = new DatagramSocket();

        String strA = ";B22DCCN290;oQFCLzJs";
        DatagramPacket dpA = new DatagramPacket(strA.getBytes(), strA.length(), inetAddress, port);
        socket.send(dpA);
        

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);

        socket.receive(dpB);

        String strB = new String(dpB.getData());
        System.out.println(strB);
        String strList[] = strB.trim().split(";");
        int sum = 0;
        for(int i = 0; i < strList[1].length(); i++){
            sum += Integer.parseInt(strList[1].charAt(i) + "");
        }   

        String sum1 = sum + "";
        sum1 = strList[0] + ";" + sum1;
        System.out.println("sum1:" + sum1);
        DatagramPacket dpC = new DatagramPacket(sum1.getBytes(), sum1.length(), inetAddress, port);
        socket.send(dpC);
        
        socket.close();
    }
}
