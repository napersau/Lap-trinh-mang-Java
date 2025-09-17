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
        String send = ";" + studentCode + ";" + qCode;
        byte [] sendData = send.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(host), port);
        socket.send(sendPacket);
        
        byte [] receiveData = new byte[2025];
        DatagramPacket receive = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receive);
    }
}
