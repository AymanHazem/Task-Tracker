package com.ayman.taskracker.domain.dto;
import com.ayman.taskracker.domain.entities.TaskPriority;
import com.ayman.taskracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;
/**
 * A Data Transfer Object (DTO) representing a task.
 * This class is used to transfer task data between different layers of the application.
 *
 * @param id          The unique identifier of the task.
 * @param title       The title of the task.
 * @param description A brief description of the task.
 * @param dueDate     The due date and time for the task.
 * @param priority    The priority level of the task, represented by {@link TaskPriority}.
 * @param status      The current status of the task, represented by {@link TaskStatus}.
 */
public record TaskDto(UUID id , String title , String description , LocalDateTime dueDate , TaskPriority priority , TaskStatus status)
{
}
