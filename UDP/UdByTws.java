package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class UdByTws {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2209;
        InetAddress inetAddress = InetAddress.getByName(host);

        DatagramSocket socket = new DatagramSocket();
        String strA = ";B22DCCN470;1UdByTws";
        DatagramPacket dpA = new DatagramPacket(strA.getBytes(), strA.length(), inetAddress, port);
        socket.send(dpA);

        byte[] buffer = new byte[1024];
        DatagramPacket dpB = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpB);

        byte[] rq = new byte[8];
        String rqId = new String(dpB.getData(), 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(dpB.getData(), 8, dpB.getLength() - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Book book = (Book) ois.readObject();

        String titleNew = "";
        String titleList[] = book.getTitle().trim().split("\\s+");
        for(int i = 0; i < titleList.length; i++){
            String tmp = titleList[i].substring(0,1).toUpperCase() 
                + titleList[i].substring(1).toLowerCase();
            titleNew += tmp + " ";
        }
        book.setTitle(titleNew.trim());

        String authorNew = "";
        String authorList[] = book.getAuthor().trim().split("\\s+");
        for(int i = 0; i < authorList.length; i++){
            authorList[i] = authorList[i].substring(0,1).toUpperCase() 
                + authorList[i].substring(1).toLowerCase();
        }
        authorNew = authorList[0].toUpperCase() + ", ";
        for(int i = 1; i < authorList.length; i++){
            authorNew += authorList[i] + " ";
        }
        book.setAuthor(authorNew.trim());

        String ISBNNew = book.getIsbn().substring(0,3) + "-" 
            + book.getIsbn().substring(3,4) + "-" + book.getIsbn().substring(4,6) + "-"
            + book.getIsbn().substring(6,12) + "-" + book.getIsbn().substring(12);
        book.setIsbn(ISBNNew);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();

        byte[]send = new byte[1024];
        System.arraycopy(rqId.getBytes(), 0, send, 0, 8);
        System.arraycopy(baos.toByteArray(), 0, send, 8, baos.size());

        DatagramPacket dpC = new DatagramPacket(send, send.length, inetAddress, port);
        socket.send(dpC);

        socket.close();
    }
}
