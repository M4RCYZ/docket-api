package br.com.todolist.task;

import br.com.todolist.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // <-- Define que é um controller
@RequestMapping("/tasks") // <-- Define a rota base
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Dentro da classe TaskController
    @PostMapping("/")
    public ResponseEntity create(@Valid @RequestBody TaskCreateDTO taskDTO, @AuthenticationPrincipal User user) {
        var createdTask = this.taskService.create(taskDTO, user);
        // Podemos retornar o DTO de resposta que já tínhamos, para ser consistente
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskDTO(createdTask));
    }

    @GetMapping("/")
    public List<TaskDTO> list(@AuthenticationPrincipal User user) {
        return this.taskService.list(user.getId());
    }

    // UPDATE -> PUT /tasks/{id}
    @PutMapping("/{id}")
    public Task update(@RequestBody Task taskDetails, @PathVariable UUID id) {
        return this.taskService.update(id, taskDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}