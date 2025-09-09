import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class q8KB0bFe{
    public static void main(String[] args) {
        String studentCode = "B22DCCN470";
        String qCode = "q8KB0bFe";
        String host = "203.162.10.109";
        int port = 2207;
        try {
            Socket socket = new Socket(host, port);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            // a.	Gửi chuỗi là mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1D25ED92"

            String send = studentCode + ";" + qCode;
            os.writeUTF(send);
            os.flush();
            System.out.println("Sent a: " + send);

            // b.	Nhận lần lượt hai số nguyên a và b từ server
            int a = is.readInt();
            int b = is.readInt();
            System.out.println("Receipted: " + a + " and " + b);

            // c.	Thực hiện tính toán tổng, tích và gửi lần lượt từng giá trị theo đúng thứ tự trên lên server
            int sum = a + b;
            int product = a * b;
            os.writeInt(sum);
            os.writeInt(product);
            os.flush();
            System.out.println("Sent sum and product for Server: " + sum + " " + product);
            // d.	Đóng kết nối và kết thúc

            os.close();
            is.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}