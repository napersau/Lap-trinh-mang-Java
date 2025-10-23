package hVPi9TcM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class hVPi9TcM {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2207;
        Socket socket = new Socket(host, port);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        String strA = "B22DCCN470;hVPi9TcM";

        dos.writeUTF(strA);
        dos.flush();

        int n = dis.readInt();
        String key = "";
        while(n != 0){
            int d = n % 2;
            n /= 2;
            key = (d + "") + key;
        }

        dos.writeUTF(key);
        dos.flush();

        System.out.println("Key:" + key);
        socket.close();
    }
}
