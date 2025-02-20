
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtility {

    public static void zipFile(String sourcePath, String zipFilePath) {
        File sourceFile = new File(sourcePath);
        try (FileOutputStream fos = new FileOutputStream(zipFilePath); ZipOutputStream zos = new ZipOutputStream(fos)) {
            if (sourceFile.isDirectory()) {
                zipDirectory(sourceFile, sourceFile.getName(), zos);
            } else {
                zipSingleFile(sourceFile, sourceFile.getName(), zos);  // Pass filename 
            }
            System.out.println("Zipping completed: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipDirectory(File folder, String parentFolder, ZipOutputStream zos) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                zipDirectory(file, parentFolder + "/" + file.getName(), zos);
            } else {
                zipSingleFile(file, parentFolder + "/" + file.getName(), zos);  // Pass filename 
            }
        }
    }

    private static void zipSingleFile(File file, String entryName, ZipOutputStream zos) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(entryName.replace("\\", "/")); 
            zos.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
        }
    }

    public static void extractZip(String zipFilePath, String outputFolderPath) {
        File destDir = new File(outputFolderPath);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File newFile = new File(outputFolderPath, entry.getName());
                if (entry.isDirectory()) {
                    newFile.mkdirs(); 
                }else {
                    new File(newFile.getParent()).mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
            System.out.println("Extraction completed: " + outputFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
