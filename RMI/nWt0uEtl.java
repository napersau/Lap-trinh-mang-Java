package RMI;

import java.lang.reflect.Array;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class nWt0uEtl {

    public static int ok = 1;
    public static void sinh(int a[], int n){
        int i = n - 2;
        while(i >= 0 && a[i] > a[i + 1]){
            i--;
        }
        if(i < 0){
            ok = 0;
        } else {
            int j = n - 1;
            while(a[i] > a[j]) j--;
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;  // Sửa lỗi: dùng tmp thay vì a[tmp]
            int l = i + 1;
            int r = n - 1;
            while(l < r){
                int tmp1 = a[l];
                a[l] = a[r];
                a[r] = tmp1;
                l++; r--;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 1099;
        String studentCode = "B22DCCN470";
        String qCode = "nWt0uEtl";
        Registry rg = LocateRegistry.getRegistry(host, port);
        DataService sv = (DataService) rg.lookup("RMIDataService");

        Object nhan = sv.requestData(studentCode, qCode);
        System.out.println("Nhan: " + nhan);
        String arrS[] = nhan.toString().trim().split(", ");
        int arr[] = new int[arrS.length];

        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(arrS[i]);
        }
        String key = "";

        int n = arr.length;
        sinh(arr, n);
        if(ok == 0){
            // Đảo ngược mảng - sửa lỗi logic
            for(int i = 0; i < n / 2; i++){
                int tmp = arr[i];              // Lưu giá trị arr[i]
                arr[i] = arr[n - 1 - i];       // Sửa: n-1-i thay vì n-i
                arr[n - 1 - i] = tmp;          // Gán giá trị đã lưu
            }
            
        }
        for(int i = 0; i < n; i++){
            arrS[i] = (arr[i] + "");
        }
        key = String.join(",", arrS);
        sv.submitData(studentCode, qCode, key);

        System.out.println("Key: " + key);
    }
}
