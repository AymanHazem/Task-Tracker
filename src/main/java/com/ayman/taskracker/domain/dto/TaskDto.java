package com.ayman.taskracker.domain.dto;

import com.ayman.taskracker.domain.entities.TaskPriority;
import com.ayman.taskracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(UUID id , String title , String description , LocalDateTime dueDate , TaskPriority priority , TaskStatus status)
{
}
