package com.ayman.taskracker.domain.dto;
import java.util.List;
import java.util.UUID;
/**
 * A Data Transfer Object (DTO) representing a list of tasks.
 * This class is used to transfer task list data between different layers of the application.
 *
 * @param id          The unique identifier of the task list.
 * @param title       The title of the task list.
 * @param description A brief description of the task list.
 * @param count       The total number of tasks in the task list.
 * @param progress    The progress of the task list, represented as a percentage.
 * @param tasks       The list of tasks associated with the task list, represented by {@link TaskDto}.
 */
public record TaskListDto(UUID id , String title , String description , Integer count , Double progress , List<TaskDto>tasks) {
}
