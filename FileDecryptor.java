
import java.io.*;
import java.nio.file.Files;
import javax.swing.*;

public class FileDecryptor {

    public static void decryptFile(String inputFilePath, String outputFilePath, String password) {
        try {
            byte[] encryptedData = Files.readAllBytes(new File(inputFilePath).toPath());
            byte[] decryptedData = AESEncryptionUtil.decrypt(encryptedData, password);
            Files.write(new File(outputFilePath).toPath(), decryptedData);

            JOptionPane.showMessageDialog(null, "Decryption successful!\nSaved as: " + outputFilePath, "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Decryption successful! File saved as: " + outputFilePath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid password or corrupted file!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Invalid password or corrupted file!");
        }
    }
}
