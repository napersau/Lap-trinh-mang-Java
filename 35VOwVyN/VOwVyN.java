import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class VOwVyN {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String studentCode = "B22DCCN470";
        String qCode = "35VOwVyN";
        String host = "203.162.10.109";
        int port = 2208;

        Socket socket = new Socket(host, port);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi với định dạng
        // "studentCode;qCode". Ví dụ: "B15DCCN999;C1234567"
        String send = studentCode + ";" + qCode;
        bw.write(send + "\n");  // Thêm newline
        bw.flush();
        System.out.println("send: " + send);

        // b. Nhận từ server một chuỗi chứa nhiều từ, các từ được phân tách bởi khoảng
        // trắng. Ví dụ: "hello world this is a test example"
        String lineS = br.readLine();
        System.out.println("received: " + lineS);  // Debug để xem server gửi gì
        
        if (lineS == null) {
            System.out.println("Server không trả về dữ liệu!");
            socket.close();
            return;
        }
        
        String arr[] = lineS.split("\\s+");

        Arrays.sort(arr, (a, b) -> {
            if (a.length() != b.length()) {
                return Integer.compare(a.length(), b.length());
            }
            return 0; // Giữ nguyên thứ tự xuất hiện nếu độ dài bằng nhau
        });

        // c. Sắp xếp các từ trong chuỗi theo độ dài, thứ tự xuất hiện. Gửi danh sách
        // các từ theo từng nhóm về server theo định dạng: "a, is, this, test, hello,
        // world, example".
        String key = String.join(", ", arr);
        bw.write(key + "\n");  // Thêm newline
        bw.flush();
        System.out.println("Sent: " + key);

        // d. Đóng kết nối và kết thúc chương trình
        br.close();
        bw.close();
        socket.close();
        System.out.println("Closed");
    }
}