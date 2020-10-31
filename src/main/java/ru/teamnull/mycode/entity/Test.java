package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OrderBy
    private Integer number;
    private String input;
    private String output;
    private float weight;

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public Test(@JsonProperty(value = "number", required = true) Integer number,
                @JsonProperty(value = "input", required = true) String input,
                @JsonProperty(value = "output") String output,
                @JsonProperty(value = "weight", required = true) float weight) {
        this.number = number;
        this.input = input;
        this.output = output;
        this.weight = weight;
    }

    @JsonIgnore
    public void setNumber(int number) {
        this.number = number;
    }

    @JsonGetter
    public int getNumber() {
        return number;
    }
}
