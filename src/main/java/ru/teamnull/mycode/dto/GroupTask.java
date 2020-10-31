package ru.teamnull.mycode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.teamnull.mycode.entity.Task;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupTask {
    private UUID group;
    private TaskDto task;
}
