package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.teamnull.mycode.model.PostprocessorType;
import ru.teamnull.mycode.model.TestType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TASK_ENTITY")
public class Task {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToMany
    @JsonIgnore
    private List<Group> groups;
    private String problem;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<Sample> samples;
    private Date deadline;
    private float timeLimit;
    private int memoryLimit;
    private TestType testType;
    private PostprocessorType postprocessorType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Test> tests;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Validation validation;
}
