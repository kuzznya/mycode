package ru.teamnull.mycode.model;

import lombok.Data;

@Data
public class SubmissionRequest {
    private Language language;
    private String code;
}
