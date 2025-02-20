
import java.io.*;
import java.nio.file.Files;
import javax.swing.*;

public class FileEncryptor {

    public static void encryptFile(String inputFilePath, String outputFilePath, String password) {
        try {
            byte[] inputData = Files.readAllBytes(new File(inputFilePath).toPath());
            byte[] encryptedData = AESEncryptionUtil.encrypt(inputData, password);
            Files.write(new File(outputFilePath).toPath(), encryptedData);

            JOptionPane.showMessageDialog(null, "Encryption successful!\nSaved as: " + outputFilePath, "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Encryption successful! File saved as: " + outputFilePath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Encryption failed!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
