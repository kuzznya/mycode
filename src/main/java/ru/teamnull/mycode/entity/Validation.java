package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VALIDATION_ENTITY")
public class Validation {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    private String generator;
    private String validator;
    private int testCount;
    @OneToOne(orphanRemoval = true)
    private Task task;
}