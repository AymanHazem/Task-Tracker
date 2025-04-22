package com.ayman.taskracker.services.impl;
import com.ayman.taskracker.domain.entities.TaskList;
import com.ayman.taskracker.repositories.TaskListRepository;
import com.ayman.taskracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService
{
    private final TaskListRepository taskListRepository;
    /**
     * Constructor for TaskListServiceImpl.
     *
     * @param taskListRepository the repository used to interact with TaskList entities.
     */
    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }
    /**
     * Retrieves all TaskLists from the repository.
     *
     * @return a list of all TaskList entities.
     */
    @Override
    public List<TaskList> listTaskLists()
    {
        return taskListRepository.findAll();
    }
    /**
     * Creates a new TaskList entity and saves it to the repository.
     *
     * @param taskList the TaskList object to be created. It must not have an ID and must have a title.
     * @return the saved TaskList entity.
     * @throws IllegalArgumentException if the TaskList already has an ID or if the title is null.
     */
    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null!=taskList.getId())
            throw new IllegalArgumentException("Task List Already has an ID!");
        if (null == taskList.getTitle())
            throw new IllegalArgumentException("Task List must has Title!");
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(null, taskList.getTitle(), taskList.getDescription(), null, now, now));
    }
    /**
     * Retrieves a TaskList entity by its ID.
     *
     * @param id the UUID of the TaskList to retrieve.
     * @return an Optional containing the TaskList if found, or empty if not found.
     */
    @Override
    public Optional<TaskList> getTaskList(UUID id)
    {
        return taskListRepository.findById(id);
    }
}
