package ru.teamnull.mycode.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.teamnull.mycode.model.Language;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {

    private static void prepareTempFile(File file) throws IOException {
        file.deleteOnExit();
        if (!file.setReadable(true, false))
            throw new IOException();
        if (!file.setWritable(true, false))
            throw new IOException();
    }

    public static File createFile(String content, String prefix, String suffix) {
        try {
            File file = File.createTempFile(prefix, suffix);
            prepareTempFile(file);
            try (FileOutputStream inputStream = new FileOutputStream(file)) {
                inputStream.write(content.getBytes());
            }
            return file;
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File IO error");
        }
    }
}
