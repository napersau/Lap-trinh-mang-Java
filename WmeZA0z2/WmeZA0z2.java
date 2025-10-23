package WmeZA0z2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WmeZA0z2 {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2206;
        Socket socket = new Socket(host, port);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        
        // Gửi mã sinh viên và mã câu hỏi
        String strA = "B22DCCN470;WmeZA0z2";
        os.write(strA.getBytes());
        os.flush();

        // Đọc số nguyên n từ server (đọc toàn bộ dữ liệu)
        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);
        String response = new String(buffer, 0, bytesRead).trim();
        int n = Integer.parseInt(response);
        System.out.println("Nhan:" + n);
        
        // Tạo chuỗi Collatz
        String sequence = "";
        int length = 0;
        
        while(n != 1){
            sequence += n + " ";
            length++;
            if(n % 2 == 0) {
                n /= 2;
            } else {
                n = n * 3 + 1;
            }
        }
        sequence += "1";
        length++; // Đếm cả số 1
        
        String result = sequence + "; " + length ;
        System.out.println("Key:" + result);
        os.write(result.getBytes());
        os.flush();

        socket.close();
        System.out.println("Close");
    }
}
