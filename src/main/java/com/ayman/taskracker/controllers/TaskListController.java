package com.ayman.taskracker.controllers;

import com.ayman.taskracker.domain.dto.TaskListDto;
import com.ayman.taskracker.domain.entities.Task;
import com.ayman.taskracker.domain.entities.TaskList;
import com.ayman.taskracker.mappers.TaskListMapper;
import com.ayman.taskracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing TaskLists.
 *
 * This controller provides endpoints for interacting with TaskList entities.
 * It uses `TaskListService` to handle business logic and `TaskListMapper`
 * to convert entities to DTOs for API responses.
 */
@RestController
@RequestMapping(path ="/api/task-lists" )
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
    /**
     * Creates a new TaskList.
     *
     * @param taskListDto the DTO containing the details of the TaskList to be created.
     * @return the DTO representing the created TaskList.
     */
    @PostMapping
    public TaskListDto createTaskList (@RequestBody TaskListDto taskListDto)
    {
        TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(createdTaskList);
    }
    /**
     * Retrieves a specific TaskList by its ID.
     *
     * @param taskListId the UUID of the TaskList to retrieve.
     * @return an Optional containing the TaskListDto if found, or an empty Optional if not found.
     */
    @GetMapping (path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList (@PathVariable("task_list_id")UUID taskListId)
    {
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }
    /**
     * Updates an existing TaskList with the provided details.
     *
     * @param taskListId the UUID of the TaskList to update.
     * @param taskListDto the DTO containing the updated details of the TaskList.
     * @return the DTO representing the updated TaskList.
     */
    @PutMapping (path = "/{task_list_id}")
    public TaskListDto updateTaskList (@PathVariable ("task_list_id") UUID taskListId , @RequestBody TaskListDto taskListDto)
    {
        TaskList updatedTaskList = taskListService.updateTaskList(taskListId,taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(updatedTaskList);
    }

    @DeleteMapping (path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable ("task_list_id") UUID taskListId)
    {
        taskListService.deleteTaskList(taskListId);
    }
}
