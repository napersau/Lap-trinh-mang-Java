package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LaqoIKdE {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";  // Sửa địa chỉ host
        int port = 1099;
        String student = "B22DCCN470";
        String code = "LaqoIKdE";
        Registry rg = LocateRegistry.getRegistry(host, port);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        
        // a. Triệu gọi phương thức requestObject để nhận đối tượng Order
        Order order = (Order) sv.requestObject(student, code);
        System.out.println("Order nhận được: " + order.getId() + ", " + 
                          order.getCustomerCode() + ", " + 
                          order.getOrderDate() + ", " + 
                          order.getShippingType());

        // b. Tạo mã orderCode theo quy tắc
        String shippingType = order.getShippingType().trim();
        String customerCode = order.getCustomerCode().trim();
        String orderDate = order.getOrderDate().trim(); // Format: yyyy-MM-dd
        
        // Lấy 2 ký tự đầu của shippingType, viết hoa
        String prefix = shippingType.substring(0, 2).toUpperCase();
        
        // Lấy 3 ký tự cuối của customerCode
        String customerSuffix = customerCode.substring(customerCode.length() - 3);
        
        // Lấy ngày và tháng từ orderDate (định dạng ddMM)
        String day = orderDate.substring(8, 10);    // Lấy ngày (dd)
        String month = orderDate.substring(5, 7);   // Lấy tháng (MM)
        String datePart = day + month;              // ddMM
        
        // c. Tạo orderCode hoàn chỉnh
        String orderCode = prefix + customerSuffix + datePart;
        
        System.out.println("OrderCode được tạo: " + orderCode);
        
        // d. Cập nhật orderCode trong đối tượng Order
        order.setOrderCode(orderCode);

        // e. Gửi đối tượng Order đã xử lý trở lại server
        sv.submitObject(student, code, order);
        
        System.out.println("Hoàn thành xử lý đơn hàng!");
    }
}
