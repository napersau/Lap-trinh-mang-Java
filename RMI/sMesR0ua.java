package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class sMesR0ua {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");

        String ma = "B22DCCN470";
        String code = "sMesR0ua";

        // a. Triệu gọi phương thức requestData để nhận dữ liệu nhị phân
        byte[] binaryData = sv.requestData(ma, code);
        System.out.println("Dữ liệu nhị phân nhận được: " + Arrays.toString(binaryData));

        // b. Chuyển đổi mảng byte thành chuỗi hex
        String hexString = bytesToHex(binaryData);
        System.out.println("Chuỗi hex: " + hexString);

        // c. Chuyển đổi chuỗi hex thành mảng byte để gửi lại server
        byte[] hexBytes = hexString.getBytes();
        System.out.println("Dữ liệu gửi lại: " + Arrays.toString(hexBytes));
        
        sv.submitData(ma, code, hexBytes);

        // d. Kết thúc chương trình
        System.out.println("Hoàn thành!");
    }
    
    // Phương thức chuyển đổi mảng byte thành chuỗi hex
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            // Chuyển đổi mỗi byte thành 2 ký tự hex
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
