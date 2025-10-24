package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class VTJumXuk {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2208;

        Socket socket = new Socket(host, port);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String s = "B22DCCN470;VTJumXuk";
        bw.write(s + "\n");
        bw.flush();

        String strB = br.readLine();
        if (strB == null) {
            System.out.println("Loi");
            socket.close();
        }
        String key = "";
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < strB.length(); i++) {
            char d = strB.charAt(i);
            if ((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z')) {
                if (map.containsKey(d)) {
                    map.put(d, map.get(d));
                } else {
                    map.put(d, 1);
                }
            }

        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            key += (entry.getKey() + "") ;
        }

        bw.write(key);
        bw.flush();

        socket.close();
        System.out.println("close");
    }
}
