package ru.teamnull.mycode.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Language {
    C(".c"),
    CPP(".cpp"),
    JAVA(".java");

    private final String extension;

    public String getExtension() {
        return extension;
    }
}
