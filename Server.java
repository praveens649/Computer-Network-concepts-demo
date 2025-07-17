import java.net.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Server {
    public static void main(String[] args) {
        final int PORT = 4000;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is waiting for image...");

            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client connected.");

                // Read image data from client
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                int imageSize = dis.readInt();
                System.out.println("Image size: " + imageSize / 1024 + " KB");

                byte[] imageBytes = new byte[imageSize];
                dis.readFully(imageBytes);

                // Convert byte array to image
                InputStream byteInput = new ByteArrayInputStream(imageBytes);
                BufferedImage receivedImage = ImageIO.read(byteInput);

                // Display image in a window
                JFrame frame = new JFrame("Received Image - Server");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new JLabel(new ImageIcon(receivedImage)));
                frame.pack();
                frame.setVisible(true);
            }

        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

