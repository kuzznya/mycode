package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.teamnull.mycode.model.ErrorStatus;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ERROR_ENTITY")
public class Error {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    private int failedTest;
    private ErrorStatus status;
}
