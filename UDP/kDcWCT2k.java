package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class kDcWCT2k {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2209;

        DatagramSocket socket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName(host);

        String sendA = ";B22DCCN470;kDcWCT2k";
        DatagramPacket dpA = new DatagramPacket(sendA.getBytes(), sendA.length(), inetAddress, port);
        socket.send(dpA);
        System.out.println("Send A: " + sendA);

        // nhan phan hoi
        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        ByteArrayInputStream bais = new ByteArrayInputStream(dpB.getData(), 0, dpB.getLength());
        DataInputStream dis = new DataInputStream(bais);

        byte[] rqId = new byte[8];
        dis.readFully(rqId);
        String rq = new String(rqId);
        System.out.println("ReId: " + rq);

        ObjectInputStream obis = new ObjectInputStream(bais);
        Student student = (Student) obis.readObject();

        // Chuan hoa
        String newName = "";
        String[] nameList = student.getName().trim().split("\\s+");
        for (int i = 0; i < nameList.length; i++) {
            String tmp = nameList[i].substring(0, 1).toUpperCase()
                    + nameList[i].substring(1).toLowerCase();
            nameList[i] = tmp;
        }
        newName = String.join(" ", nameList);
        student.setName(newName);

        String email = nameList[nameList.length - 1].toLowerCase();
        for (int i = 0; i < nameList.length - 1; i++) {
            email += nameList[i].substring(0, 1).toLowerCase();
        }
        email += "@ptit.edu.vn";
        student.setEmail(email);
        System.out.println("enail:" + email);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bout);
        dos.write(rqId);

        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(student);
        oos.flush();

        byte[] sendData = bout.toByteArray();
        DatagramPacket sendBack = new DatagramPacket(
                sendData, sendData.length,
                inetAddress, port);
        socket.send(sendBack);

        System.out.println("\nĐã gửi lại khách hàng đã chuẩn hóa cho server!");
        socket.close();

    }
}
