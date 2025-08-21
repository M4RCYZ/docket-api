package br.com.todolist.task;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskDTO {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private LocalDateTime createdAt;

    // Construtor para facilitar a conversão de Task para TaskDTO
    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.startAt = task.getStartAt();
        this.endAt = task.getEndAt();
        this.priority = task.getPriority();
        this.createdAt = task.getCreatedAt();
    }
}