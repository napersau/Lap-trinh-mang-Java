package rBccllgw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class rBccllgw {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String studentCode = "B22DCCN470";
        String qCode = "rBccllgw";
        String host = "203.162.10.109";
        int port = 2208;

        Socket socket = new Socket(host, port);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String send = studentCode + ";" + qCode;
        bw.write(send);
        bw.newLine(); // Add newline character
        bw.flush();
        System.out.println("Sent:" + send);

        String receivedData = br.readLine();
        if (receivedData == null) {
            System.out.println("No response from server or connection closed");
            socket.close();
            return;
        }
        
        System.out.println("Received: " + receivedData);
        String []s = receivedData.split("\\s+");
        String key = "";

        for(int i = 0; i < s.length; i++){
            String tmp = new StringBuilder(s[i]).reverse().toString();
            String tmp1 = "";
            int cnt = 1;
            for(int j = 0; j < tmp.length() - 1; j++){
                if(tmp.charAt(j) == tmp.charAt(j + 1)) cnt++;
                else {
                    if(cnt == 1) {
                        tmp1 += tmp.charAt(j);
                        continue;
                    }
                    tmp1 += tmp.charAt(j) + (cnt + "");
                    cnt = 1;
                }
            }
            if(cnt == 1) {
                tmp1 += tmp.charAt(s[i].length() - 1);
            }
            else tmp1 += tmp.charAt(s[i].length() - 1) + (cnt + "");
            key += tmp1 + " ";

        }

        
        bw.write(key.trim());
        bw.newLine(); // Add newline character
        bw.flush();
        System.out.println("Sent: " + key.trim());
        
        bw.close();
        br.close();
        socket.close();
        System.out.println("Close");
    }
}