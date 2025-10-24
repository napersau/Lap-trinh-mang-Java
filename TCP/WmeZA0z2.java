package TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WmeZA0z2 {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2206;

        Socket socket = new Socket(host, port);
        String code = "B22DCCN470;WmeZA0z2";

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        os.write(code.getBytes());
        os.flush();

        byte[] buffer = new byte[1024];
        is.read(buffer);
        String strB = new String(buffer);

        System.out.println("strB: " + strB);

        int n = Integer.parseInt(strB.trim());
        System.out.println("N: " + n);

        int cnt = 1;
        String key = (n + "") + " ";
        while(n != 1){
            if(n % 2 == 0) {
                n /= 2;
                cnt++;
                key += (n + "") + " ";
            } else {
                n = 3 * n + 1;
                cnt++;
                key += (n + "") + " ";
            }
        }
        key = key.trim() + "; " + (cnt + "");
        System.out.println("Key: " + key);

        os.write((key).getBytes());

        socket.close();
        System.out.println("Close");

    }
}
