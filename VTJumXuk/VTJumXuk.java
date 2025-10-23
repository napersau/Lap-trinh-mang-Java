package VTJumXuk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class VTJumXuk {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2208;
        Socket socket = new Socket(host, port);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String strA = "B22DCCN470;VTJumXuk";
        bw.write(strA + "\n");
        bw.flush();

        String nhan = br.readLine();
        if (nhan == null) {
            System.out.println("Server không phản hồi hoặc đóng kết nối");
            socket.close();
            return;
        }
        System.out.println("Nhận từ server: " + nhan);
        String key = "";
        for(int i = 0; i < nhan.length(); i++){
            char d = nhan.charAt(i);
            if((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z')){
                if(key.contains(d + "")) continue;
                key += (d + "");
            }
        }

        bw.write(key);
        bw.flush();
        System.out.println("Key:" + key);

        socket.close();
        System.out.println("Close");

    }
}
