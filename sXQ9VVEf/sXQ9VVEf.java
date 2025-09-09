package mmt.sXQ9VVEf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class sXQ9VVEf {
    public static void main(String[] args) {
        String studentCode = "B22DCCN470";
        String qCode = "sXQ9VVEf";
        String host = "203.162.10.109";
        int port = 2206;

        try (Socket socket = new Socket(host, port)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream os = socket.getOutputStream();

            // a. Gửi mã SV và qCode
            String sendStr = studentCode + ";" + qCode + "\n";
            os.write(sendStr.getBytes());
            os.flush();
            System.out.println("Sent: " + sendStr.trim());

            // b. Nhận chuỗi số
            String numbersStr = br.readLine();
            System.out.println("Received: " + numbersStr);

            // c. Xử lý
            String[] arrS = numbersStr.split(",");
            int[] arr = new int[arrS.length];
            int sum = 0;
            for (int i = 0; i < arrS.length; i++) {
                arr[i] = Integer.parseInt(arrS[i].trim());
                sum += arr[i];
            }

            double avg = (double) sum / arr.length;
            double target = 2 * avg;

            double lech = Math.abs(arr[0] + arr[1] - target);
            int num1 = Math.min(arr[0], arr[1]);
            int num2 = Math.max(arr[0], arr[1]);

            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    double diff = Math.abs(arr[i] + arr[j] - target);
                    if (diff < lech) {
                        lech = diff;
                        num1 = Math.min(arr[i], arr[j]);
                        num2 = Math.max(arr[i], arr[j]);
                    }
                }
            }

            // d. Gửi kết quả rồi kết thúc
            String key = num1 + "," + num2 + "\n";
            os.write(key.getBytes());
            os.flush();
            System.out.println("Sent result: " + key.trim());

            // 👉 Không đọc thêm từ server nữa
            os.close();
            
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
