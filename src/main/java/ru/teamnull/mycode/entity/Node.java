package ru.teamnull.mycode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Node {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private NodeType type;
    private UUID previous;
    @OneToMany
    @JsonIgnore
    private List<Node> next;

    @JsonProperty("next")
    public List<UUID> getNext() {
        return next.stream()
                .map(Node::getId)
                .collect(Collectors.toList());
    }
}
