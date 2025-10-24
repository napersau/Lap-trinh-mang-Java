package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class yWFYc695 {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        
        String ma = "B22DCCN290";
        String code = "yWFYc695";

        // a. Triệu gọi phương thức requestData để nhận dữ liệu nhị phân
        // byte[] data = sv.requestData(ma, code);
        // System.out.println("Dữ liệu nhận được: " + Arrays.toString(data));
        // System.out.println("Chuỗi văn bản: " + new String(data));
        
        // // b. Mã hóa XOR với khóa "PTIT"
        // String keyString = "PTIT";
        // byte[] key = keyString.getBytes(); // [80, 84, 73, 84]
        // System.out.println("Khóa mã hóa: " + Arrays.toString(key));
        
        // // Thực hiện XOR từng byte với khóa (lặp lại khóa nếu cần)
        // byte[] encodedData = new byte[data.length];
        // for (int i = 0; i < data.length; i++) {
        //     // Lấy byte tương ứng trong khóa (lặp lại khóa nếu data dài hơn)
        //     byte keyByte = key[i % key.length];
        //     // Thực hiện phép XOR
        //     encodedData[i] = (byte) (data[i] ^ keyByte);
        // }
        
        // System.out.println("Dữ liệu sau mã hóa XOR: " + Arrays.toString(encodedData));
        
        // // c. Gửi dữ liệu đã mã hóa về server
        // sv.submitData(ma, code, encodedData);

        // // d. Kết thúc
        // System.out.println("Hoàn thành mã hóa XOR!");

        byte[] nhan = sv.requestData(ma, code);
        System.out.println("Nhan:" + nhan.toString());
        String key = "PTIT";
        byte []keyB = key.getBytes();

        byte []cheo = new byte[nhan.length];
        for(int i = 0; i < cheo.length; i++){
            cheo[i] = keyB[i % key.length()];
        }

        for(int i = 0; i < cheo.length; i++){
            cheo[i] = (byte) (cheo[i] ^ nhan[i]);
        }

        sv.submitData(ma, code, cheo);
        
    }
}
