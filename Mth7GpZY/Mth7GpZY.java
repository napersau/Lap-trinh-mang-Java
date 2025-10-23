import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Mth7GpZY {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2207;
        DatagramSocket socket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName(host);
        String strA = ";B22DCCN470;Mth7GpZY";
        DatagramPacket dpA = new DatagramPacket(strA.getBytes(), strA.length(), inetAddress, port);
        socket.send(dpA);

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        String strB = new String(dpB.getData());
        String []listB = strB.trim().split(";");
        String []arrB = listB[1].trim().split(",");
        int []soB = new int[arrB.length];

        for(int i = 0; i < soB.length; i++){
            soB[i] = Integer.parseInt(arrB[i]);
        }

        Arrays.sort(soB);

        String key  = listB[0] + ";" + (soB[soB.length - 1] + "") + "," + (soB[0] + "");
        DatagramPacket dpC = new DatagramPacket(key.getBytes(), key.length(), inetAddress, port);
        socket.send(dpC);

        socket.close();



    }
}