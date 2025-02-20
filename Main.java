
import java.io.File;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        while (true) {

            String[] options = {"Encrypt File/Folder", "Decrypt File"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option", "File Encryption",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                encryptFileOrFolder();
            } else if (choice == 1) {
                decryptEncryptedFile();
            } else {
                System.out.println("Exiting program...");
                break;
            }
        }
    }

    private static void encryptFileOrFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setDialogTitle("Select a File or Folder to Encrypt");
        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();
        String password = JOptionPane.showInputDialog("Enter password:");
        JFileChooser saveChooser = new JFileChooser();
        saveChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (saveChooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File saveLocation = saveChooser.getSelectedFile();
        try {
            if (selectedFile.isDirectory()) {
                String zipPath = saveLocation + "/" + selectedFile.getName() + ".zip";
                ZipUtility.zipFile(selectedFile.getAbsolutePath(), zipPath);
                FileEncryptor.encryptFile(zipPath, zipPath + ".enc", password);
                new File(zipPath).delete();
            } else {
                FileEncryptor.encryptFile(selectedFile.getAbsolutePath(), saveLocation + "/" + selectedFile.getName() + ".enc", password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptEncryptedFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File encryptedFile = fileChooser.getSelectedFile();
        String password = JOptionPane.showInputDialog("Enter password:");
        String decryptedPath = encryptedFile.getAbsolutePath().replace(".enc", "");

        try {
            FileDecryptor.decryptFile(encryptedFile.getAbsolutePath(), decryptedPath, password);
            if (new File(decryptedPath).exists()) {
                if (decryptedPath.endsWith(".zip")) {
                    ZipUtility.extractZip(decryptedPath, decryptedPath.replace(".zip", ""));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid password! Decryption failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
