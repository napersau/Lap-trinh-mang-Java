import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SbOk0KNS_TCP_Client {
    public static void main(String[] args) {
        String studentCode = "B22DCCN470";
        String qCode = "SbOk0KNS";
        String host = "203.162.10.109";
        int port = 2208;

        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress(host, port), 5000);
            socket.setSoTimeout(5000);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            //a) Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;EC4F899B"
            String request = studentCode + ";" + qCode;
            writer.write(request);
            writer.newLine();
            writer.flush();
            System.out.println("Sent: " + request);

            //b) Nhận một chuỗi ngẫu nhiên là danh sách các một số tên miền từ server
            // Ví dụ: giHgWHwkLf0Rd0.io, I7jpjuRw13D.io, wXf6GP3KP.vn, MdpIzhxDVtTFTF.edu, TUHuMfn25chmw.vn, HHjE9.com, 4hJld2m2yiweto.vn, y2L4SQwH.vn, s2aUrZGdzS.com, 4hXfJe9giAA.edu
            String domainsLine = reader.readLine();
            System.out.println("Receipted from Server: " + domainsLine);

            // c) Tìm kiếm các tên miền .edu và gửi lên server Ví dụ: MdpIzhxDVtTFTF.edu, 4hXfJe9giAA.edu
            String []domains = domainsLine.split(",\\s*");
            List<String> eduDoamins = new ArrayList<>();
            for(String d : domains){
                if(d.trim().endsWith(".edu")){
                    eduDoamins.add(d);
                }
            }
            String result = String.join(", ", eduDoamins);
            System.out.println("Domains edu: " + result);

            writer.write(result);
            writer.newLine();
            writer.flush();
            System.out.println("Sent to Server");

            // d) Đóng kết nối và kết thúc chương trình.
            reader.close();
            writer.close();
            socket.close();


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}