package DykwivYc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class DykwivYc {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String studentCode = "B22DCCN470";
        String qCode = "DykwivYc";
        String host = "203.162.10.109";
        int port = 2207;

        Socket socket = new Socket(host, port);
        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        // a. Gửi mã sinh viên + qCode
        String send = studentCode + ";" + qCode;
        os.writeUTF(send);
        os.flush();
        System.out.println("Send: " + send);

        // b. Nhận mảng số nguyên
        String lineS = is.readUTF();
        System.out.println("lineS: " + lineS);

        String[] s = lineS.split(",");
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }

        // c. Tính số lần đổi chiều và tổng độ biến thiên
        int cnt = 0;
        int bt = 0;
        String tmp = "";
        if (a.length > 1) {
            if (a[1] > a[0]) tmp = "t";
            else tmp = "g";
            for (int i = 1; i < a.length; i++) {
                if (a[i] > a[i - 1] && tmp.equals("g")) {
                    tmp = "t";
                    cnt++;
                }
                if (a[i] < a[i - 1] && tmp.equals("t")) {
                    tmp = "g";
                    cnt++;
                }
                bt += Math.abs(a[i] - a[i - 1]);
            }
        }

        // Gửi kết quả lên server
        os.writeInt(cnt);
        os.writeInt(bt);
        os.flush();
        System.out.println("cnt bt: " + cnt + " " + bt);

        is.close();
        os.close();
        socket.close();
        System.out.println("Close");
    }
}
