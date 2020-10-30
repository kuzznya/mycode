package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.teamnull.mycode.model.SubmissionStatus;

import javax.persistence.*;
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
    @ManyToOne(cascade = CascadeType.ALL)
    private Task task;
    @ManyToOne
    private User user;
    private Date timestamp;
    private SubmissionStatus status;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Error> errors;
    private int points;
}
