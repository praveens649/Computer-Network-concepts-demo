import java.net.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "192.168.1.4";
        final int SERVER_PORT = 4000;
        final String IMAGE_PATH = "image.jpg";

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            System.out.println("Client is running.");

            // Read image from local disk
            System.out.println("Reading image from disk...");
            BufferedImage img = ImageIO.read(new File(IMAGE_PATH));

            // Convert image to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();

            // Send image to server
            System.out.println("Sending image to server...");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(imageBytes.length); // Send image size
            dos.write(imageBytes);           // Send image bytes
            System.out.println("Image sent to server.");

            dos.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
