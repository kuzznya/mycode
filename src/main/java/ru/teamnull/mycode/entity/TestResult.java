package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.teamnull.mycode.model.SubmissionStatus;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TESTRESULT_ENTITY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestResult {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "submission_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Submission submission;
    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    @JsonIdentityInfo(property = "number", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Test test;
    @ManyToOne
    @JoinColumn(name = "validation_id", referencedColumnName = "id")
    private Validation validation;
    private Integer validationNumber;
    private SubmissionStatus status;
}
