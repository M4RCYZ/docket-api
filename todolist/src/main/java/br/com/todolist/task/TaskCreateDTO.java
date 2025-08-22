// Local: src/main/java/br/com/todolist/task/TaskCreateDTO.java

package br.com.todolist.task;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 50, message = "O título deve ter no máximo 50 caracteres")
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @NotNull(message = "A data de início é obrigatória")
    @Future(message = "A data de início deve ser uma data futura")
    private LocalDateTime startAt;

    @NotNull(message = "A data de término é obrigatória")
    @Future(message = "A data de término deve ser uma data futura")
    private LocalDateTime endAt;

    @NotBlank(message = "A prioridade é obrigatória")
    private String priority;

    // A anotação foi movida daqui de cima...

    // ...para ficar diretamente sobre o método que ela valida.
    @AssertTrue(message = "A data de início deve ser anterior à data de término")
    public boolean isStartDateBeforeEndDate() {
        if (startAt == null || endAt == null) {
            return true;
        }
        return startAt.isBefore(endAt);
    }
}