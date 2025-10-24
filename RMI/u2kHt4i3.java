package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class u2kHt4i3 {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);

        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");

        String student = "B22DCCN290";
        String code = "u2kHt4i3";

        ProductX product = (ProductX) sv.requestObject(student, code);

        int discount = 0;
        String dsCode = product.getDiscountCode();

        for(int i = 0; i < dsCode.length(); i++){
            char d = dsCode.charAt(i);
            if(d >= '1' && d <= '9'){
                discount += Integer.parseInt((d + ""));
            }
        }

        product.setDiscount(discount);

        sv.submitObject(student, code, product);


    }
}
