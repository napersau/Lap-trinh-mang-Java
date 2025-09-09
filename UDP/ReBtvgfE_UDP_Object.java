package UDP;
import UDP.Product;
import java.io.*;
import java.net.*;

public class ReBtvgfE_UDP_Object {
    public static void main(String[] args) {
        String studentCode = "B22DCCN470";
        String qCode = "ReBtvgfE";

        int serverPort = 2209;
        String serverHost = "203.162.10.109";

        try (DatagramSocket socket = new DatagramSocket()) {
            // ---- Gửi request ----
            String request = ";" + studentCode + ";" + qCode;
            byte[] sendData = request.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length,
                    InetAddress.getByName(serverHost), serverPort);
            socket.send(sendPacket);

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
            Product p = (Product) ois.readObject();
            System.out.println("Nhận từ server: " + p);

            // ---- Sửa dữ liệu ----
            p.setName(fixName(p.getName()));
            p.setQuantity(fixQuantity(p.getQuantity()));
            System.out.println("Sau khi sửa: " + p);

            // ---- Gửi lại ----
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.write(reqIdBytes);

            ObjectOutputStream oos = new ObjectOutputStream(dos);
            oos.writeObject(p);
            oos.flush();

            byte[] dataToSend = baos.toByteArray();
            DatagramPacket sendBack = new DatagramPacket(
                    dataToSend, dataToSend.length,
                    InetAddress.getByName(serverHost), serverPort);
            socket.send(sendBack);

            System.out.println("Đã gửi lại sản phẩm đã sửa cho server!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String fixName(String wrongName) {
        String[] parts = wrongName.trim().split("\\s+");
        if (parts.length > 1) {
            String tmp = parts[0];
            parts[0] = parts[parts.length - 1];
            parts[parts.length - 1] = tmp;
        }
        return String.join(" ", parts);
    }

    private static int fixQuantity(int wrongQuantity) {
        String s = new StringBuilder(String.valueOf(wrongQuantity)).reverse().toString();
        return Integer.parseInt(s);
    }
}
