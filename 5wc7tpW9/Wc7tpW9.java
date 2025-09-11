import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Wc7tpW9 {

    static boolean snt(int n){
        if(n < 2) return false; 
        if(n == 2) return true;
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        String studentCode = "B22DCCN470";
        String qCode = "5wc7tpW9";
        String host = "203.162.10.109";
        int port = 2206; // Fixed port number

        Socket socket = new Socket(host, port);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        
        String send = studentCode + ";" + qCode;
        os.write(send.getBytes());
        os.flush();
        System.out.println("Sent: " + send);

        // Read response properly
        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);
        String str = new String(buffer, 0, bytesRead).trim();
        System.out.println("Received: " + str);

        String[] s = str.split(",");
        int[] a = new int[s.length];
        for(int i = 0; i < s.length; i++){
            a[i] = Integer.parseInt(s[i].trim());
        }
        
        int sum = 0;
        for(int x : a){
            if(snt(x)) {
                sum += x;
            }
        }

        // Send result as string
        String result = String.valueOf(sum);
        os.write(result.getBytes());
        os.flush();
        System.out.println("Sent sum: " + sum);

        os.close();
        is.close();
        socket.close();
        System.out.println("Close");
    }
}