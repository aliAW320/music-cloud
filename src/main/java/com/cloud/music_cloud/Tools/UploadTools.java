package com.cloud.music_cloud.Tools;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;
public class UploadTools {
    public static String base64ToMp3(String base64Data) throws IOException {
            String uniqueName = UUID.randomUUID().toString() + ".mp3";

            File directory = new File("music_files");
            if (!directory.exists()) {
                directory.mkdirs(); // create directory if it doesn't exist
            }

            File file = new File(directory, uniqueName);


            byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(decodedBytes);
            }

            return uniqueName;
        }


        public static String mp3ToBase64FromMusicFiles(String fileName) throws IOException {
            File file = new File("music_files", fileName);  // Load from project-root/music_files/

            if (!file.exists()) {
                throw new IOException("File not found: " + file.getAbsolutePath());
            }

            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        }
    }




