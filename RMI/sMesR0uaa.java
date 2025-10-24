package RMI;

import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class sMesR0uaa {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 1099;
        Registry rg = LocateRegistry.getRegistry(host, port);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");

        byte [] buffer = sv.requestData("B22DCCN470", "sMesR0ua");

        String hexa = toHex(buffer);

        sv.submitData("B22DCCN470", "sMesR0ua", hexa.getBytes());

    }

    public static String toHex(byte[] buffer){
        String key = "";
        for(byte b : buffer){
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1){
                key = key + "0";
            }
            key += hex;
        }
        return key;

    }
}
