package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class zmJ4ymHD {
    public static void main(String[] args) throws Exception {

        String host = "203.162.10.109";
        int port = 2209;
        Socket socket = new Socket(host, port);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        String strA = "B22DCCN470;zmJ4ymHD";
        oos.writeObject(strA);
        oos.flush();

        Customer customer = (Customer) ois.readObject();

        String newName = "";
        String nameList[] = customer.getName().trim().split("\\s+");

        for(int i = 0; i < nameList.length; i++){
            nameList[i] = nameList[i].substring(0,1).toUpperCase() 
                + nameList[i].substring(1).toLowerCase();
        }

        newName = nameList[nameList.length - 1].toUpperCase() + ", ";
        for(int i = 0; i < nameList.length - 1; i++){
            newName += nameList[i] + " ";
        }

        customer.setName(newName.trim());

        String newBirth = "";
        String birthList[] = customer.getDayOfBirth().trim().split("-");
        newBirth = birthList[1] + "/" + birthList[0] + "/" + birthList[2];

        customer.setDayOfBirth(newBirth);

        String newUser = "";
        for(int i = 0; i < nameList.length - 1; i++){
            newUser += nameList[i].substring(0,1).toLowerCase();
        }
        newUser += nameList[nameList.length - 1].toLowerCase();

        customer.setUserName(newUser);
        System.out.println("new user:" + newUser);

        oos.writeObject(customer);

        oos.flush();

        socket.close();
        System.out.println("Close");

    }
}
