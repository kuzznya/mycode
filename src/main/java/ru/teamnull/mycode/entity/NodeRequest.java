package ru.teamnull.mycode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeRequest {
    private String name;
    private NodeType type;
    private UUID previous;
    private List<UUID> next;
}
