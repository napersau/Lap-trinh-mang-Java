package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class zmJ4ymHD2 {

    public static void chuanhoa(String s[] , int n){
        for(int i = 0; i < n; i++){
            s[i] = s[i].substring(0,1).toUpperCase() 
                + s[i].substring(1).toLowerCase();
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2209;

        Socket socket = new Socket(host, port);

        
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        String code = "B22DCCN470;zmJ4ymHD";
        System.out.println("Code: " + code);
        oos.writeUTF(code);
        oos.flush();

        Customer customer = (Customer) ois.readObject();
        String newName = "";
        String nameList[] = customer.getName().trim().split("\\s+");
        chuanhoa(nameList, nameList.length);

        newName = nameList[nameList.length - 1].toUpperCase() + ", ";
        for(int i = 0; i < nameList.length - 1; i++){
            newName += nameList[i] + " ";
        }

        customer.setName(newName.trim());

        System.out.println("Name: " + newName.trim());

        // Xử lý ngày sinh: mm-dd-yyyy -> dd/mm/yyyy
        String birth = customer.getDayOfBirth().trim();
        String[] birthParts = birth.split("-");
        String newBirth = birthParts[1] + "/" + birthParts[0] + "/" + birthParts[2];

        customer.setDayOfBirth(newBirth);
        System.out.println("Birth: " + newBirth);

        // Tạo username: chữ cái đầu của các từ (trừ từ cuối) + từ cuối viết thường
        String userNew = "";
        for(int i = 0; i < nameList.length - 1; i++){
            userNew += (nameList[i].charAt(0) + "").toLowerCase();
        }
        userNew += nameList[nameList.length - 1].toLowerCase();
        customer.setUserName(userNew);

        System.out.println("Username: " + userNew);

        oos.writeObject(customer);
        oos.flush();

        socket.close();

    }
}
