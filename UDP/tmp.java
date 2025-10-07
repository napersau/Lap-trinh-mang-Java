package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class tmp {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2207;

        DatagramSocket socket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName(host);

        String sendA = ";B22DCCN470;9EgM40LW";
        DatagramPacket dpA = new DatagramPacket(sendA.getBytes(), sendA.length(), inetAddress, port);
        socket.send(dpA);

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        ByteArrayInputStream bais = new ByteArrayInputStream(dpB.getData(), 0, dpB.getLength());
        DataInputStream dis = new DataInputStream(bais);
        byte[] rqId = new byte[8];
        dis.readFully(rqId);

        String rq = new String(rqId);

        ObjectInputStream ois = new ObjectInputStream(bais);
        Customer customer = (Customer) ois.readObject();


        // gui

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(rqId);

        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(customer);
        oos.flush();

        byte []sendData = baos.toByteArray();
        DatagramPacket dpC = new DatagramPacket(sendData, sendA.length(), inetAddress, port);
        socket.send(dpC);




        
        socket.close();

        
    }
}
