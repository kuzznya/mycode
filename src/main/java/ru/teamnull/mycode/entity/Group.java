package ru.teamnull.mycode.entity;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Task> tasks;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> students;
}
