package G2339v;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class G2339v {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2208;
        DatagramSocket socket = new DatagramSocket();
        String strA = ";B22DCCN470;54G2339v";
        InetAddress inetAddress = InetAddress.getByName(host);
        DatagramPacket dpA = new DatagramPacket(strA.getBytes(), strA.length(), inetAddress, port);
        socket.send(dpA);

        byte [] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        String strB = new String(dpB.getData());
        String []listB = strB.trim().split(";");
        String []soB = listB[1].trim().split(",");
        
        String key = "";
        int nho = 0;
        int n = soB[0].length();
        int m = soB[1].length();
        if(n < m){
            for(int i = 0; i < m - n; i++){
                soB[0] = "0" + soB[0];
            }
        }
        else{
            for(int i = 0; i < n - m; i++){
                soB[1] = "0" + soB[1];
            }
        }
        for(int i = soB[0].length() - 1; i >=0; i--){
            int a1 = Integer.parseInt(soB[0].charAt(i) + "");
            int a2 = Integer.parseInt(soB[1].charAt(i) + "");
            int d = a1 + a2 + nho;
            key = ((d % 2) + "") + key;
            nho = d / 2;
        }
        if(nho == 1) key += "1" + key;
        int sum = 0;
        System.out.println("key:" + key);
        for(int i = key.length() - 1; i >= 0; i--){
            int d = Integer.parseInt(key.charAt(i) + "");
            sum += d * Math.pow(2,(key.length() - 1 - i));
        }
        String keyC = listB[0] + ";" + (sum + "");
        DatagramPacket dpC = new DatagramPacket(keyC.getBytes(), keyC.length(), inetAddress, port);
        socket.send(dpC);

        socket.close();
    }
}
