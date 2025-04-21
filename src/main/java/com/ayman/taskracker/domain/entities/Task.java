package com.ayman.taskracker.domain.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a task entity in the task tracking system.
 * This entity is mapped to the "tasks" table in the database.
 */
@Entity
@Table(name = "tasks")
@Data
public class Task {

    /**
     * Unique identifier for the task.
     * Generated automatically using UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    /**
     * Title of the task.
     * Cannot be null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Description of the task.
     * Optional field.
     */
    @Column(name = "description")
    private String description;

    /**
     * Due date and time for the task.
     * Optional field.
     */
    @Column(name = "due_date")
    private LocalDateTime dueDate;

    /**
     * Current status of the task.
     * Cannot be null.
     */
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    /**
     * Priority level of the task.
     * Cannot be null.
     */
    @Column(name = "priority", nullable = false)
    private TaskPriority priority;

    /**
     * The task list to which this task belongs.
     * Mapped as a many-to-one relationship with lazy fetching.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    /**
     * Timestamp when the task was created.
     * Cannot be null.
     */
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    /**
     * Timestamp when the task was last updated.
     * Cannot be null.
     */
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    /**
     * Default constructor for the Task entity.
     */
    public Task() {
    }
    public Task(UUID id, String title, String description, LocalDateTime dueDate, TaskStatus status, TaskPriority priority, TaskList taskList, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.taskList = taskList;
        this.created = created;
        this.updated = updated;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(dueDate, task.dueDate) &&
                status == task.status &&
                priority == task.priority &&
                Objects.equals(taskList, task.taskList) &&
                Objects.equals(created, task.created) &&
                Objects.equals(updated, task.updated);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate, status, priority, taskList, created, updated);
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
                ", taskList=" + taskList +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}