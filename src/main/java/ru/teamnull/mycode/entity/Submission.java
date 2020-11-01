package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import ru.teamnull.mycode.model.Language;
import ru.teamnull.mycode.model.SubmissionStatus;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SUBMISSION_ENTITY")
@Builder
public class Submission {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    @ManyToOne
    private Task task;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String code;
    private Language language;
    @CreationTimestamp
    private Date timestamp;
    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<CheckResult> checkResults;
    private int points;
}
