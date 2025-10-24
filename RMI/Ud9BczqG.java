package RMI;

import java.lang.reflect.Array;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class Ud9BczqG {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService"); // Sửa: RMIDataService (chữ M)

        String stuent = "B22DCCN290";
        String code = "Ud9BczqG";

        Object nhan = sv.requestData(stuent, code);
        int n = Integer.parseInt(nhan.toString());

        System.out.println("Nhan: " + nhan);

        int cnt = 0;
        int key[] = new int[4];
        for(int i = 0; i < 4; i++){
            key[i] = 0;
        }
        String key1 = "";

        while( n != 0){
            while(n >= 10) {
                cnt++;
                key[0] ++;
                n-=10;
                key1 += "10,";
            }
            while(n >= 5) {
                cnt++;
                key[1] ++;
                n-=5;
                key1 += "5,";
            }
            while(n >= 2) {
                cnt++;
                key[2] ++;
                n-=2;
                key1 += "2,";
            }
            while(n >= 1) {
                cnt++;
                key[3] ++;
                n-=1;
                key1 += "1,";
            }
        }

        if(cnt > 0){
            key1 = (cnt + "") + "; " + key1;
            key1 = key1.substring(0, key1.length() - 1);
        }
        else key1 = "-1";

        System.out.println("Key: " + key1);

        sv.submitData(stuent, code, key1);

    }
}
