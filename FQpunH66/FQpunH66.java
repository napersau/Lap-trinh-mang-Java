package FQpunH66;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FQpunH66 {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String studentCode = "B22DCCN470";
        String qCode = "FQpunH66";
        String host = "203.162.10.109";
        int port = 2207;

        Socket socket = new Socket(host, port);
        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        // a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode"
        String send = studentCode + ";" + qCode;
        os.writeUTF(send);
        os.flush();
        System.out.println("Sent: " + send);
        
        // b. Nhận một số nguyên hệ thập phân từ server
        int number = is.readInt();
        System.out.println("Received: " + number);

        // c. Chuyển đổi số nguyên nhận được sang hai hệ cơ số 8 và 16        
        // Tính toán để debug
        String octal = Integer.toOctalString(number);
        String hex = Integer.toHexString(number).toUpperCase();
        
        // Gửi hệ 8 trước
        os.writeUTF(octal + ";" + hex);
        os.flush();
        System.out.println("Sent octal: " + octal);
        
        // Gửi hệ 16 sau
        // os.writeUTF(hex);
        // os.flush();
        // System.out.println("Sent hex: " + hex);

        // d. Đóng kết nối và kết thúc chương trình
        os.close();
        is.close();
        socket.close();
        System.out.println("Closed");
    }
}