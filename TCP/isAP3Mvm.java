package TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class isAP3Mvm {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        String studentCode = "B22DCCN470";
        String qCode = "isAP3Mvm";
        String host = "203.162.10.109";
        int port = 2209;

        Socket socket = new Socket(host, port);
        String sendCode = studentCode + ";" + qCode;

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        oos.writeObject(sendCode);
        oos.flush();
        System.out.println("Sent: " + sendCode);

        Product product = (Product) ois.readObject();

        int discount = 0;
        String iPrice = ((int) product.getPrice()) + "";
        for(int i = 0; i < iPrice.length(); i++){
            int d = Integer.parseInt(iPrice.charAt(i) + "");
            discount += d;
        }

        product.setDiscount(discount);

        System.out.println("Discount: " + discount);
        System.out.println("Product: " + product);

        // Gửi lại product đã được cập nhật về server
        oos.writeObject(product);
        oos.flush();

        oos.close();
        ois.close();
        socket.close();
        System.out.println("Close");

    }
}
