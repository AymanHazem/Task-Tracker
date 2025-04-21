package com.ayman.taskracker.mappers;

import com.ayman.taskracker.domain.dto.TaskListDto;
import com.ayman.taskracker.domain.entities.TaskList;

public interface TaskListMapper
{
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
