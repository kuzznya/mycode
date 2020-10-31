package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TEST_ENTITY")
public class Test {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @JsonIgnore
    private Task task;
    private int number;
    private String input;
    private String output;
    private float weight;

    @JsonCreator
    public Test(int number, String input, String output, float weight) {
        this.number = number;
        this.input = input;
        this.output = output;
        this.weight = weight;
    }
}
