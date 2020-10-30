package ru.teamnull.mycode.entity;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ENTITY")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String name;
    private String surname;
}
