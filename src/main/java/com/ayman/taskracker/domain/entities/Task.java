package com.ayman.taskracker.domain.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table (name = "tasks")
@Data
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id" , nullable = false , updatable = false)
    private UUID id;
    @Column (name = "title" , nullable = false)
    private String title;
    @Column (name = "description")
    private String description;
    @Column (name = "due_date")
    private LocalDateTime dueDate;
    @Column (name = "status", nullable = false)
    private TaskStatus status;
    @Column (name = "priority" ,nullable = false)
    private TaskPriority priority;
    @Column (name = "created",nullable = false)
    private LocalDateTime created;
    @Column (name = "updated",nullable = false)
    private LocalDateTime updated;
    public Task() {
    }
    public Task(UUID id, String title, String description, LocalDateTime dueDate, TaskStatus status, TaskPriority priority, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.created = created;
        this.updated = updated;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(dueDate, task.dueDate) && status == task.status && priority == task.priority && Objects.equals(created, task.created) && Objects.equals(updated, task.updated);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate, status, priority, created, updated);
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", priority=" + priority +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
