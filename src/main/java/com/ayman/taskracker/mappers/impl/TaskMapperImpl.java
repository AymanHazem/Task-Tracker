package com.ayman.taskracker.mappers.impl;
import com.ayman.taskracker.domain.dto.TaskDto;
import com.ayman.taskracker.domain.entities.Task;
import com.ayman.taskracker.mappers.TaskMapper;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
/**
 * Implementation of the {@link TaskMapper} interface.
 * This class provides methods to map between {@link Task} and {@link TaskDto}.
 */
@Component
public class TaskMapperImpl implements TaskMapper
{
    /**
     * Maps a {@link TaskDto} to a {@link Task}.
     *
     * @param taskDto The {@link TaskDto} to map from.
     * @return The mapped {@link Task}.
     */
    @Override
    public Task fromDto(TaskDto taskDto)
    {
        return new Task(taskDto.id(),taskDto.title(),taskDto.description(),taskDto.dueDate(),taskDto.status(),taskDto.priority(),null,null,null);
    }
    /**
     * Maps a {@link Task} to a {@link TaskDto}.
     *
     * @param task The {@link Task} to map from.
     * @return The mapped {@link TaskDto}.
     */
    @Override
    public TaskDto toDto(Task task)
    {
        return new TaskDto(task.getId(),task.getTitle(),task.getDescription(),task.getDueDate(),task.getPriority(),task.getStatus());
    }
}
