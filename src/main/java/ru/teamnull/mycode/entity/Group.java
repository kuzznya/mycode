package ru.teamnull.mycode.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GROUP_ENTITY")
public class Group {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @ManyToMany
    private List<Task> tasks;
    @OneToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
    private List<User> students;
}
