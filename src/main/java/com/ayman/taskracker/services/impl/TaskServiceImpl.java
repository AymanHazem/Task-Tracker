package com.ayman.taskracker.services.impl;

import com.ayman.taskracker.domain.entities.Task;
import com.ayman.taskracker.domain.entities.TaskList;
import com.ayman.taskracker.domain.entities.TaskPriority;
import com.ayman.taskracker.domain.entities.TaskStatus;
import com.ayman.taskracker.repositories.TaskListRepository;
import com.ayman.taskracker.repositories.TaskRepository;
import com.ayman.taskracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Service
public class TaskServiceImpl implements TaskService
{
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId)
    {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId,Task task)
    {
        if (null != task.getId())
            throw new IllegalArgumentException("Task Already has an ID!");
        if (null == task.getTitle() || task.getTitle().isBlank())
            throw new IllegalArgumentException("Task must have a title!");
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(()->new IllegalArgumentException("Invalid Task List ID provided!"));
        LocalDateTime now = LocalDateTime.now();
        return taskRepository.save(new Task(null,task.getTitle(),task.getDescription(),task.getDueDate(),taskStatus,taskPriority,taskList,now,now));
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId)
    {
        return taskRepository.findByTaskListIdAndId(taskListId,taskId);
    }

    @Override
    public Task updateTask(UUID taskListId,UUID taskId, Task task)
    {
        if (null == task.getId())
            throw new IllegalArgumentException("Task Must have ID !");
        if (!Objects.equals(taskId,task.getId()))
            throw new IllegalArgumentException("Tasks ID's Not Match");
        if (null == task.getTitle())
            throw new IllegalArgumentException("Task Must have Title !");
        if (null == task.getPriority())
            throw new IllegalArgumentException("Task Must have Priority !");
        if (null == task.getStatus())
            throw new IllegalArgumentException("Task Must have Status !");
        Task exTask = taskRepository.findByTaskListIdAndId(taskListId,taskId ).orElseThrow(()->new IllegalArgumentException("Invalid Task List ID provided!"));
        exTask.setTitle(task.getTitle());
        exTask.setDescription(task.getDescription());
        exTask.setDueDate(task.getDueDate() );
        exTask.setStatus(task.getStatus());
        exTask.setPriority(task.getPriority());
        exTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(exTask);
    }
}
