package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NZbD44gu {
    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2209;

        DatagramSocket socket = new DatagramSocket();

        InetAddress inetAddress = InetAddress.getByName(host);

        String sendB = ";B22DCCN470;NZbD44gu";
        DatagramPacket dpB = new DatagramPacket(sendB.getBytes(), sendB.length(), inetAddress, port);

        socket.send(dpB);
        System.out.println("SendB:" + sendB);

        // ---- Nhận phản hồi ----
        byte[] receiveData = new byte[65535];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
        DataInputStream dis = new DataInputStream(bais);

        byte[] reqIdBytes = new byte[8];
        dis.readFully(reqIdBytes);
        String requestId = new String(reqIdBytes);

        ObjectInputStream ois = new ObjectInputStream(dis);
        Customer customer = (Customer) ois.readObject();
        System.out.println("Nhận từ server: " + customer);
        System.out.println("Thông tin khách hàng ban đầu:");
        System.out.println("ID: " + customer.getId());
        System.out.println("Code: " + customer.getCode());
        System.out.println("Name: " + customer.getName());
        System.out.println("Day of Birth: " + customer.getDayOfBirth());
        System.out.println("Username: " + customer.getUserName());

        // Chuẩn hóa tên
        // Ví dụ: nguyen van hai duong -> DUONG, Nguyen Van Hai
        String originalName = customer.getName();
        String[] nameParts = originalName.trim().split("\\s+");
        String lastName = nameParts[nameParts.length - 1].toUpperCase();
        
        StringBuilder formattedName = new StringBuilder(lastName);
        formattedName.append(", ");
        
        for (int i = 0; i < nameParts.length - 1; i++) {
            String part = nameParts[i];
            formattedName.append(part.substring(0, 1).toUpperCase());
            formattedName.append(part.substring(1).toLowerCase());
            if (i < nameParts.length - 2) {
                formattedName.append(" ");
            }
        }
        
        customer.setName(formattedName.toString());
        
        // Chuyển đổi định dạng ngày sinh từ mm-dd-yyyy thành dd/mm/yyyy
        String originalDate = customer.getDayOfBirth();
        String[] dateParts = originalDate.split("-");
        if (dateParts.length == 3) {
            String month = dateParts[0];
            String day = dateParts[1];
            String year = dateParts[2];
            String newDate = day + "/" + month + "/" + year;
            customer.setDayOfBirth(newDate);
        }
        
        // Tạo username từ họ tên
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < nameParts.length - 1; i++) {
            username.append(nameParts[i].toLowerCase().charAt(0));
        }
        username.append(nameParts[nameParts.length - 1].toLowerCase());
        customer.setUserName(username.toString());
        
        System.out.println("\nThông tin khách hàng sau khi chuẩn hóa:");
        System.out.println("ID: " + customer.getId());
        System.out.println("Code: " + customer.getCode());
        System.out.println("Name: " + customer.getName());
        System.out.println("Day of Birth: " + customer.getDayOfBirth());
        System.out.println("Username: " + customer.getUserName());

        // ---- Gửi lại ----
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.write(reqIdBytes);

        ObjectOutputStream oos = new ObjectOutputStream(dos);
        oos.writeObject(customer);
        oos.flush();

        byte[] dataToSend = baos.toByteArray();
        DatagramPacket sendBack = new DatagramPacket(
                dataToSend, dataToSend.length,
                inetAddress, port);
        socket.send(sendBack);

        System.out.println("\nĐã gửi lại khách hàng đã chuẩn hóa cho server!");
        
        // Đóng socket và kết thúc chương trình
        socket.close();
    }
}