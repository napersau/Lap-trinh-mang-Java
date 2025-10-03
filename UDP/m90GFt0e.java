package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class m90GFt0e {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2209;

        DatagramSocket socket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName(host);

        // a. gửi chuỗi ";studentCode;qCode"
        String sendA = ";B22DCCN470;m90GFt0e";
        DatagramPacket dpA = new DatagramPacket(sendA.getBytes(), sendA.length(), inetAddress, port);
        socket.send(dpA);
        System.out.println("SendA: " + sendA);

        // b. nhận response
        byte[] buffer = new byte[2048];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        ByteArrayInputStream bais = new ByteArrayInputStream(dpB.getData(), 0, dpB.getLength());

        // đọc 8 byte requestId
        byte[] rqId = new byte[8];
        bais.read(rqId);
        String rq = new String(rqId);
        System.out.println("RequestId: " + rq);

        // đọc object Book
        ObjectInputStream ois = new ObjectInputStream(bais);
        Book book = (Book) ois.readObject();
        ois.close();

        // --- c. chuẩn hóa ---
        // Title: viết hoa chữ cái đầu
        String[] titleL = book.getTitle().trim().split("\\s+");
        StringBuilder newTitle = new StringBuilder();
        for (String w : titleL) {
            newTitle.append(w.substring(0, 1).toUpperCase())
                    .append(w.substring(1).toLowerCase())
                    .append(" ");
        }
        book.setTitle(newTitle.toString().trim());

        // Author: "Họ Đệm, Tên"
        String[] authorL = book.getAuthor().trim().split("\\s+");
        if (authorL.length > 1) {
            StringBuilder hodem = new StringBuilder();
            // Process all words except the last one (họ và đệm)
            for (int i = 0; i < authorL.length - 1; i++) {
                String word = authorL[i];
                if (word.length() > 0) {
                    hodem.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase());
                    
                    if (i < authorL.length - 2) {
                        hodem.append(" ");
                    }
                }
            }
            
            // Process the last word (tên)
            String ten = authorL[authorL.length - 1];
            ten = ten.substring(0, 1).toUpperCase() + ten.substring(1).toLowerCase();
            
            // Combine with the correct format: "Họ Đệm, Tên"
            book.setAuthor(hodem.toString() + ", " + ten);
            System.out.println("Formatted author: " + book.getAuthor());
        }

        // ISBN: theo định dạng 978-3-16-148410-0 (giả sử luôn đủ 13 số)
        String is = book.getIsbn().trim();
        if (is.length() == 13) {
            String newIsbn = is.substring(0, 3) + "-" + is.substring(3, 4) + "-" +
                             is.substring(4, 6) + "-" + is.substring(6, 12) + "-" +
                             is.substring(12);
            book.setIsbn(newIsbn);
        }


        // kiểm tra trước khi gửi lại
        System.out.println("||" + book.getTitle()
                + " | " + book.getAuthor()
                + " | " + book.getIsbn()
                + " | " + book.getPublishDate());

        // d. gửi lại: 8 byte rqId + Book object
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        bous.write(rqId); // ghi requestId trước
        ObjectOutputStream oos = new ObjectOutputStream(bous);
        oos.writeObject(book);
        oos.flush();

        byte[] sendData = bous.toByteArray();
        DatagramPacket dpD = new DatagramPacket(sendData, sendData.length, inetAddress, port);
        socket.send(dpD);

        // đóng socket
        socket.close();
    }
}
