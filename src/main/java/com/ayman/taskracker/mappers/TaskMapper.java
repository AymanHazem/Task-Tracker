package com.ayman.taskracker.mappers;

import com.ayman.taskracker.domain.dto.TaskDto;
import com.ayman.taskracker.domain.entities.Task;

public interface TaskMapper
{
    Task fromDto(TaskDto taskDto);
    TaskDto toDto (Task task);
}
