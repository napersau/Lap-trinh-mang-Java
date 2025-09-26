package JaY5lCcc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class JaY5lCcc {
    public static void main(String[] args) throws IOException {
        String studentCode = "B22DCCN470";
        String qCode = "JaY5lCcc";
        String host  = "203.162.10.109";
        int port = 2208;

        DatagramSocket socket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName(host);
        String send = ";" + studentCode + ";" + qCode;
        DatagramPacket dgSend = new DatagramPacket(send.getBytes(), send.length(), inetAddress, port);
        socket.send(dgSend);

        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);

        String s = new String(dpNhan.getData());
        System.out.println("Receipt:  " + s);

        String []sList = s.split(";");
        String []sList2 = sList[1].split("\\s+");

        System.out.println("nhan 1:" + sList[0]);
        System.out.println("nhan 1:" + sList[1]);

        String key = "";
        for(String tmp : sList2){
            String tmp1 = tmp.substring(0,1).toUpperCase() + 
                tmp.substring(1).toLowerCase();
            key += tmp1 + " ";
        }
        key = sList[0] + ";" + key.trim();
        DatagramPacket dgSendc = new DatagramPacket(key.getBytes(), key.length(), inetAddress, port);
        socket.send(dgSendc);

        System.out.println("Send: " + key);

        socket.close();
        System.out.println("Close");
    }
}
