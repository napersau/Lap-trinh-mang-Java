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
        
        StringBuilder strS = new StringBuilder(receivedData);
        System.out.println("Receipted: " + strS);

        String str = strS.reverse().toString();

        String key = "";
        int i = 0;
        int n = str.length();
        
        while(i < n){
            if(str.charAt(i) == ' ') {
                key += " ";
                i++;
            } else {
                int cnt = 1;
                char currentChar = str.charAt(i);
                
                // Count consecutive characters
                while(i + 1 < n && str.charAt(i) == str.charAt(i + 1)) {
                    i++;
                    cnt++;
                }
                
                // Only add count if > 1
                if(cnt > 1) {
                    key += currentChar + "" + cnt;
                } else {
                    key += currentChar;
                }
                i++;
            }
        }
        
        bw.write(key);
        bw.newLine(); // Add newline character
        bw.flush();
        System.out.println("Sent: " + key);
        
        bw.close();
        br.close();
        socket.close();
        System.out.println("Close");
    }
}