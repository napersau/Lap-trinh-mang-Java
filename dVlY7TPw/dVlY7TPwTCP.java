package dVlY7TPw;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class dVlY7TPwTCP {
    public static void main(String[] args) {
        String studentCode = "B22DCCN470";
        String qCode = "dVlY7TPw";
        String host = "203.162.10.109";
        int port = 2206;
        int timeout = 5000; // 5 seconds

        try (Socket socket = new Socket()) {
            // Connect to the server
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.setSoTimeout(timeout);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // a) Send studentCode and qCode
            String request = studentCode + ";" + qCode; // Try without \n first
            os.write(request.getBytes());
            os.flush();
            System.out.println("Sent: " + request);

            // b) Read server response
            StringBuilder response = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;
            long startTime = System.currentTimeMillis();

            try {
                // Read until data is available or timeout occurs
                while ((bytesRead = is.read(buffer)) != -1) {
                    String chunk = new String(buffer, 0, bytesRead);
                    response.append(chunk);
                    System.out.println("Received chunk: " + chunk); // Debug raw data
                    if (chunk.contains("\n") || chunk.contains("|")) { // Stop on newline or delimiter
                        break;
                    }
                    // Break if taking too long (additional safety)
                    if (System.currentTimeMillis() - startTime > timeout) {
                        System.err.println("Reading stopped due to timeout.");
                        break;
                    }
                }
            } catch (java.net.SocketTimeoutException e) {
                System.err.println("Socket timeout while reading response: " + e.getMessage());
                System.out.println("Partial response (if any): " + response.toString());
                return;
            }

            String numberLine = response.toString().trim();
            if (numberLine.isEmpty()) {
                System.err.println("No valid data received from server.");
                return;
            }
            System.out.println("Received from Server: " + numberLine);

            // c) Calculate sum of numbers
            String[] numbers = numberLine.split("\\|");
            int sum = 0;
            for (String s : numbers) {
                try {
                    sum += Integer.parseInt(s.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in response: " + s);
                    return;
                }
            }
            System.out.println("Sum: " + sum);

            // Send sum to server
            String sentSum = sum + "\n"; // Append newline for sum
            os.write(sentSum.getBytes());
            os.flush();
            System.out.println("Sent to Server: " + sentSum.trim());

        } catch (java.net.SocketException e) {
            System.err.println("Socket error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}