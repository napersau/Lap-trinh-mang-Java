package QLbLdKwd_UDP_DataType;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.xml.crypto.Data;

public class QLbLdKwd_UDP_DataType {
    public static void main(String[] args) {
        String studentCode = "B22DCCN470";
        String qCode = "QLbLdKwd"; 

        int serverPort = 2207;
        String serverHost = "203.162.10.109"; // nếu server chạy máy khác thì đổi IP

        DatagramSocket socket = null;

        try {
            // Tạo socket
            socket = new DatagramSocket();

            // ===== B1: Gửi chuỗi ";studentCode;qCode" =====
            String message = ";" + studentCode + ";" + qCode;
            byte[] sendData = message.getBytes();
            InetAddress serverAddress = InetAddress.getByName(serverHost);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("Send: " + message);

            // ===== B2: Nhận chuỗi "requestId;a1,a2,...,a50" =====
            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Give server: " + response);

            // Tách chuỗi theo định dạng
            String[] parts = response.split(";");
            String requestId = parts[0];
            String[] numbersStr = parts[1].split(",");

            int[] numbers = new int[numbersStr.length];
            for (int i = 0; i < numbersStr.length; i++) {
                numbers[i] = Integer.parseInt(numbersStr[i].trim());
            }

            // ===== B3: Tìm max và min =====
            int max = numbers[0], min = numbers[0];
            for (int n : numbers) {
                if (n > max) max = n;
                if (n < min) min = n;
            }

            // Chuẩn bị kết quả gửi lại
            String result = requestId + ";" + max + "," + min;
            byte[] resultData = result.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, serverAddress, serverPort);
            socket.send(resultPacket);
            System.out.println("Send result: " + result);

            // ===== B4: Đóng socket =====
            socket.close();
            System.out.println("Close client.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
