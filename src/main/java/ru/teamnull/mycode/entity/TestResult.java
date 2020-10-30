package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.teamnull.mycode.model.SubmissionStatus;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TESTRESULT_ENTITY")
public class TestResult {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id", nullable = false)
    @JsonIdentityInfo(property = "number", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Test test;
    private SubmissionStatus status;
}
