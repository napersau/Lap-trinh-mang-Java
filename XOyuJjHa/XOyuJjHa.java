package XOyuJjHa;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class XOyuJjHa {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2206;
        Socket socket = new Socket(host, port);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        String code = "B22DCCN290;XOyuJjHa";
        os.write(code.getBytes());
        os.flush();

        byte [] buffer = new byte[1024];
        is.read(buffer);

        String s = new String(buffer);
        System.out.println("String:" + s);

        String []strList = s.trim().split("\\|");
        int sum = 0;
        for(int i = 0; i < strList.length; i++){
            int d = Integer.parseInt( strList[i]);
            sum += d;
        }
        os.write((sum + "").getBytes());
        System.out.println("Sum" + sum);

        os.flush();
        socket.close();
    }
}
