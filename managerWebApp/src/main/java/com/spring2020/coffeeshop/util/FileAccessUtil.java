package com.spring2020.coffeeshop.util;

import com.spring2020.coffeeshop.exception.FileAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileAccessUtil {

    public static File createFile(String path) {
        File convertFile = new File(path);
        try {
            convertFile.createNewFile();
            return convertFile;
        } catch (IOException e) {
            throw new FileAccessException(e.getMessage());
        }
    }

    public static void copyFile(File destination, MultipartFile source) {
        try (FileOutputStream outputStream = new FileOutputStream(destination);) {
            outputStream.write(source.getBytes());
        } catch (IOException ex) {
            throw new FileAccessException(ex.getMessage());
        }
    }
}
