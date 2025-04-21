package com.ayman.taskracker.services;

import com.ayman.taskracker.domain.entities.TaskList;

import java.util.List;


public interface TaskListService
{
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
}
