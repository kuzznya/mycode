package ru.teamnull.mycode.model;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
public enum Language {
    C(".c"),
    CPP(".cpp"),
    JAVA(".java");

    private final String extension;

    public String getExtension() {
        return extension;
    }

    public static Language fromExtension(String extension) {
        switch (extension.toLowerCase()) {
            case ".c":
                return C;
            case ".cpp":
                return CPP;
            case ".java":
                return JAVA;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown file extension");
        }
    }
}
