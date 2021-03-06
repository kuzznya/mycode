package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.core.annotation.Order;
import ru.teamnull.mycode.model.PostprocessorType;
import ru.teamnull.mycode.model.CheckType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString(exclude = {"groups", "samples", "tests", "validation"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TASK_ENTITY")
public class Task {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<Group> groups;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String problem;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<Sample> samples;
    @OrderBy
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date deadline;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<Submission> submissions;

    private float timeLimit;
    private int memoryLimit;
    private CheckType checkType;
    private PostprocessorType postprocessorType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Test> tests;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Validation validation;
}
