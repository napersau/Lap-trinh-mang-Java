package ZngIHH6y;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collections;

public class ZngIHH6y  {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2208;
        InetAddress inetAddress = InetAddress.getByName(host);
        DatagramSocket socket = new DatagramSocket();

        String sendA = ";B22DCCN470;ZngIHH6y";
        DatagramPacket dpA = new DatagramPacket(sendA.getBytes(), sendA.length(),
            inetAddress, port);
        socket.send(dpA);
        System.out.println("Send:" + sendA);

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        String s = new String(dpB.getData(), 0, dpB.getLength()).trim();
        String list1[] = s.split(";");
        String list2[] = list1[1].split("\\s+");
        
        // Sắp xếp theo thứ tự từ điển ngược không phân biệt hoa thường
        Arrays.sort(list2, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        
        String key = list1[0] + ";";
        key += String.join(",", list2);
        System.out.println("Key:" + key);

        DatagramPacket dpC = new DatagramPacket(key.getBytes(), key.length(),
            inetAddress, port);
        socket.send(dpC);

        socket.close();
        System.out.println("close");
    }
}
