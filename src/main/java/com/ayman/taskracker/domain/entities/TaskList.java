package com.ayman.taskracker.domain.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * Represents a task list entity in the system.
 * This entity is mapped to the "task_lists" table in the database.
 */
@Entity
@Table(name = "task_lists")
@Data
public class TaskList
{
    /**
     * The unique identifier of the task list.
     * This field is auto-generated using the UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    /**
     * The title of the task list.
     * This field is mandatory.
     */
    @Column(name = "title", nullable = false)
    private String title;
    /**
     * A brief description of the task list.
     */
    @Column(name = "description")
    private String description;
    /**
     * The list of tasks associated with this task list.
     * Tasks are mapped by the "taskList" field in the Task entity.
     * Cascade operations include REMOVE and PERSIST.
     */
    @OneToMany(mappedBy = "taskList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Task> tasks;
    /**
     * The timestamp when the task list was created.
     * This field is mandatory.
     */
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    /**
     * The timestamp when the task list was last updated.
     * This field is mandatory.
     */
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;
    /**
     * Default constructor for the TaskList entity.
     */
    public TaskList() {
    }
    /**
     * Constructs a TaskList with the specified details.
     *
     * @param id          The unique identifier of the task list.
     * @param title       The title of the task list.
     * @param description A brief description of the task list.
     * @param tasks       The list of tasks associated with the task list.
     * @param created     The timestamp when the task list was created.
     * @param updated     The timestamp when the task list was last updated.
     */
    public TaskList(UUID id, String title, String description, List<Task> tasks, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.created = created;
        this.updated = updated;
    }
    /**
     * Checks if this TaskList is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(id, taskList.id) &&
                Objects.equals(title, taskList.title) &&
                Objects.equals(description, taskList.description) &&
                Objects.equals(tasks, taskList.tasks) &&
                Objects.equals(created, taskList.created) &&
                Objects.equals(updated, taskList.updated);
    }
    /**
     * Computes the hash code for this TaskList.
     *
     * @return The hash code of the TaskList.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tasks, created, updated);
    }
    /**
     * Returns a string representation of the TaskList.
     *
     * @return A string representation of the TaskList.
     */
    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}