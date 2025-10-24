package EQaJAvlj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class EQaJAvlj {
    public static void main(String[] args) throws Exception {
        
        String host = "203.162.10.109";
        int port = 2207;
        Socket socket = new Socket(host, port);
        String code = "B22DCCN290;EQaJAvlj";

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(code);
        dos.flush();

        int number = dis.readInt();

        String np = Integer.toBinaryString(number);
        String h16 = Integer.toHexString(number);

        String key = np + ";" + h16.toUpperCase();
        dos.writeUTF(key);;
        dos.flush();
        System.out.println("Key" + key);

        socket.close();
        System.out.println("Close");
    }
}
