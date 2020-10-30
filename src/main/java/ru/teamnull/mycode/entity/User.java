package ru.teamnull.mycode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Target;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ENTITY")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;

}
