package TCP;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Client8aWlKwkZ {
    public static void main(String[] args) {
        String host = "203.162.10.109"; 
        int port = 2209;

        String studentCode = "B22DCCN470"; 
        String qCode = "8aWlKwkZ";       

        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(5000); // timeout 5 giây

            // Luồng đối tượng
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // a. Gửi chuỗi "studentCode;qCode"
            String msg = studentCode + ";" + qCode;
            oos.writeObject(msg);
            oos.flush();
            System.out.println("Đã gửi: " + msg);

            // b. Nhận đối tượng Address
            Address addr = (Address) ois.readObject();
            System.out.println("Nhận từ server: " + addr);

            // c. Chuẩn hóa dữ liệu
            addr.setAddressLine(normalizeAddressLine(addr.getAddressLine()));
            addr.setPostalCode(normalizePostalCode(addr.getPostalCode()));

            System.out.println("Sau chuẩn hóa: " + addr);

            // d. Gửi lại đối tượng đã chuẩn hóa
            oos.writeObject(addr);
            oos.flush();

            // Đóng kết nối
            oos.close();
            ois.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm chuẩn hóa addressLine
    private static String normalizeAddressLine(String line) {
        if (line == null) return "";
        // bỏ ký tự đặc biệt, chỉ giữ chữ, số và khoảng trắng
        line = line.replaceAll("[^a-zA-Z0-9\\s]", " ");
        // bỏ khoảng trắng thừa
        line = line.trim().replaceAll("\\s+", " ");
        // viết hoa chữ cái đầu
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(line, " ");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1).toLowerCase())
              .append(" ");
        }
        return sb.toString().trim();
    }

    // Hàm chuẩn hóa postalCode
    private static String normalizePostalCode(String code) {
        if (code == null) return "";
        return code.replaceAll("[^0-9\\-]", "");
    }
}
