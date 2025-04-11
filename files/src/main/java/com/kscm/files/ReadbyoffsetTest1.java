package com.kscm.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadbyoffsetTest1 {
    public static void main(String[] args) {
        File file = new File("README.md");
        try (InputStream is = new FileInputStream(file)) {
            if (file.length() > Integer.MAX_VALUE) {
                throw new IOException("File is too large.");
            }

            int offset = 0;
            int bytesRead;
            byte[] bytes = new byte[(int) file.length()];
            while (offset < bytes.length
                    && (bytesRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += bytesRead;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
