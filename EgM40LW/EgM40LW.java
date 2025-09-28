package EgM40LW;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class EgM40LW {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2207;
        String sendA = ";B22DCCN470;9EgM40LW";
        InetAddress inetAddress = InetAddress.getByName(host);

        DatagramSocket socket = new DatagramSocket();

        DatagramPacket dpA = new DatagramPacket(sendA.getBytes(), sendA.length(), 
                            inetAddress, port );
        socket.send(dpA);
        System.out.println("Gui A:" + sendA);

        byte[] byteB = new byte[1024];
        DatagramPacket strB = new DatagramPacket(byteB, byteB.length);
        socket.receive(strB);

        String s = new String(strB.getData());
        System.out.println("s" + s);
        String listS[] = s.trim().split(";");
        String key = "";

        String list2[] = listS[1].trim().split(",");

        int list3[] = new int[list2.length];
        int t = 0;
        for(String tmp : list2){
            System.out.println(Integer.parseInt(tmp));
            list3[t] = Integer.parseInt(tmp);
            t++;
        }
        Arrays.sort(list3);

        int max2 = list3[list3.length - 2];
        int min2 = list3[1];

        key = listS[0] + ";" + (max2 + "") + "," + (min2 + "");
        System.out.println("Key:" + key);
        DatagramPacket dpC = new DatagramPacket(key.getBytes(), key.length(),
                            inetAddress, port);
        socket.send(dpC);

        socket.close();
        System.out.println("Close");

    }

}
