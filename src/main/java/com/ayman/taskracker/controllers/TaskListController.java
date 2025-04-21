package com.ayman.taskracker.controllers;

import com.ayman.taskracker.domain.dto.TaskListDto;
import com.ayman.taskracker.domain.entities.Task;
import com.ayman.taskracker.mappers.TaskListMapper;
import com.ayman.taskracker.services.TaskListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * REST controller for managing TaskLists.
 *
 * This controller provides endpoints for interacting with TaskList entities.
 * It uses `TaskListService` to handle business logic and `TaskListMapper`
 * to convert entities to DTOs for API responses.
 */
@RestController
@RequestMapping(path ="/task-lists" )
public class TaskListController
{
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;
/**
     * Constructor for `TaskListController`.
     *
     * @param taskListService the service used to handle TaskList business logic.
     * @param taskListMapper the mapper used to convert TaskList entities to DTOs.
     */
    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }
    /**
     * Retrieves a list of all TaskLists.
     *
     * @return a list of TaskListDto objects representing all TaskLists.
     */
    @GetMapping
    public List<TaskListDto> listTaskLists ()
    {
        return taskListService.listTaskLists().stream().map(taskListMapper::toDto).toList();
    }
}
