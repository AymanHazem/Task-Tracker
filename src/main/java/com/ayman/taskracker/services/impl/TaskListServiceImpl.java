package com.ayman.taskracker.services.impl;
import com.ayman.taskracker.domain.entities.TaskList;
import com.ayman.taskracker.repositories.TaskListRepository;
import com.ayman.taskracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null!=taskList.getId())
            throw new IllegalArgumentException("Task List Already has an ID!");
        if (null == taskList.getTitle())
            throw new IllegalArgumentException("Task List must has Title!");
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(null, taskList.getTitle(), taskList.getDescription(), null, now, now));
    }
}
