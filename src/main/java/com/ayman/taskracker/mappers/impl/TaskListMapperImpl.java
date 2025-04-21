package com.ayman.taskracker.mappers.impl;
import com.ayman.taskracker.domain.dto.TaskListDto;
import com.ayman.taskracker.domain.entities.Task;
import com.ayman.taskracker.domain.entities.TaskList;
import com.ayman.taskracker.domain.entities.TaskStatus;
import com.ayman.taskracker.mappers.TaskListMapper;
import com.ayman.taskracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
/**
 * Implementation of the {@link TaskListMapper} interface.
 * This class provides methods to map between {@link TaskList} and {@link TaskListDto}.
 */
@Component
public class TaskListMapperImpl implements TaskListMapper
{
    private final TaskMapper taskMapper;
    /**
     * Constructs a new instance of {@code TaskListMapperImpl}.
     *
     * @param taskMapper The {@link TaskMapper} used for mapping tasks.
     */
    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }
    /**
     * Maps a {@link TaskListDto} to a {@link TaskList}.
     *
     * @param taskListDto The {@link TaskListDto} to map from.
     * @return The mapped {@link TaskList}.
     */
    @Override
    public TaskList fromDto(TaskListDto taskListDto)
    {
        return new TaskList
                (taskListDto.id(),taskListDto.title(),taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks->tasks.stream().map(taskMapper::fromDto)
                                .toList())
                        .orElse(null)
                , null,
                null);
    }
    /**
     * Maps a {@link TaskList} to a {@link TaskListDto}.
     *
     * @param taskList The {@link TaskList} to map from.
     * @return The mapped {@link TaskListDto}.
     */
    @Override
    public TaskListDto toDto(TaskList taskList)
    {
        return new TaskListDto
                (
                        taskList.getId(),
                        taskList.getTitle(),
                        taskList.getDescription(),
                        Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
                        calculateTaskListProgress(taskList.getTasks()),
                        Optional.ofNullable(taskList.getTasks()).map(tasks -> tasks.stream().map(taskMapper::toDto).toList()).orElse(null));
    }
    /**
     * Calculates the progress of a task list based on the status of its tasks.
     *
     * @param tasks The list of {@link Task} objects to calculate progress for.
     * @return The progress as a percentage, or {@code null} if the task list is empty.
     */
    private Double calculateTaskListProgress (List<Task> tasks)
    {
        if (null == tasks)
            return null;
        long closedTaskCount = tasks.stream().filter(task-> TaskStatus.CLOSED == task.getStatus()).count();
        return (double) closedTaskCount/tasks.size();
    }
}