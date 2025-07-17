import java.io.*;
import java.net.*;

public class Serverr {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(4000);
      System.out.println("Server is waiting for text...");

      Socket socket = serverSocket.accept();
      System.out.println("Client connected.");

      // Read text from client
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String message = reader.readLine();

      System.out.println("Received from client: " + message);

      // Close everything
      reader.close();
      try {
        Thread.sleep(10000); // Wait 10 seconds
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      socket.close();
      serverSocket.close();
    } catch (IOException e) {
      System.out.println("Server error: " + e.getMessage());
    }
  }
}
