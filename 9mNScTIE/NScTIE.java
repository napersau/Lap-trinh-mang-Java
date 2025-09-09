import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NScTIE {
    public static void main(String[] args) throws UnknownHostException, IOException {

        String studentCode = "B22DCCN470";
        String qCode = "9mNScTIE";
        String host = "203.162.10.109";
        int port = 2207;

        Socket socket = new Socket(host, port);
        String sendStr = studentCode + ";" + qCode;
        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        // a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
        // Ví dụ: "B10DCCN003;C6D7E8F9"
        os.writeUTF(sendStr);
        os.flush();
        System.out.println("Sent Str: " + sendStr);

        // b. Nhận lần lượt:
        // • Một số nguyên k là độ dài đoạn.
        // • Chuỗi chứa mảng số nguyên, các phần tử được phân tách bởi dấu phẩy ",".
        // Ví dụ: Nhận k = 3 và "1,2,3,4,5,6,7,8".
        int k = is.readInt();
        String strLine = is.readUTF();
        String arr[] = strLine.split(",");
        String key = "";
        for (int i = 0; i < arr.length; i += k) {
            if (i + k <= arr.length) { // đoạn đủ k phần tử
                for (int j = k - 1; j >= 0; j--) {
                    key += arr[i + j] + ",";
                }
            } else { // đoạn cuối không đủ k phần tử
                for (int j = arr.length - 1; j >= i; j--) {
                    key += arr[j] + ",";
                }
            }
        }
        System.out.println("strLine: " + strLine);
        System.out.println("k :" + k);

        if (key.charAt(key.length() - 1) == ',')
            key = key.substring(0, key.length() - 1);

        // c. Thực hiện chia mảng thành các đoạn có độ dài k và đảo ngược mỗi đoạn, sau
        // đó gửi mảng đã xử lý lên server. Ví dụ: Với k = 3 và mảng "1,2,3,4,5,6,7,8",
        // kết quả là "3,2,1,6,5,4,8,7". Gửi chuỗi kết quả "3,2,1,6,5,4,8,7" lên server.
        os.writeUTF(key);
        os.flush();
        System.out.println("Sent reverse: " + key);

        // d. Đóng kết nối và kết thúc chương trình
        is.close();
        os.close();
        socket.close();
        System.out.println("Closed");
    }
}
