package com.ayman.taskracker.services.impl;
import com.ayman.taskracker.domain.entities.TaskList;
import com.ayman.taskracker.repositories.TaskListRepository;
import com.ayman.taskracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    /**
     * Updates an existing TaskList entity with the provided details.
     *
     * @param taskListid the UUID of the TaskList to update.
     * @param taskList the TaskList object containing the updated details.
     * @return the updated TaskList entity.
     * @throws IllegalArgumentException if the provided TaskList does not have an ID,
     *                                  if the ID does not match the provided taskListid,
     *                                  or if the TaskList is not found in the repository.
     */
    @Override
    public TaskList updateTaskList(UUID taskListid, TaskList taskList)
    {
        if (null == taskList.getId())
            throw new IllegalArgumentException("Task Must have ID !");
        if (!Objects.equals(taskList.getId(),taskListid))
            throw new IllegalArgumentException("Not Allowed");
        TaskList exTaskList = taskListRepository.findById(taskListid).orElseThrow(()->new IllegalArgumentException("Task List Not Found!"));
        exTaskList.setTitle(taskList.getTitle());
        exTaskList.setDescription(taskList.getDescription());
        exTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(exTaskList);
    }
}
