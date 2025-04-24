package com.ayman.taskracker.services.impl;

import com.ayman.taskracker.domain.entities.Task;
import com.ayman.taskracker.repositories.TaskRepository;
import com.ayman.taskracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class TaskServiceImpl implements TaskService
{
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId)
    {
        return taskRepository.findByTaskListId(taskListId);
    }
}
