package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String problem;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Sample> samples;
    private Date deadline;
    private float timeLimit;
    private int memoryLimit;
    private TestType testType;
    private PostprocessorType postprocessorType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    List<Test> tests;
}
