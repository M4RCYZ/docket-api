package br.com.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // <-- Importante

import java.util.UUID;

@RestController // <-- Define que é um controller
@RequestMapping("/tasks") // <-- Define a rota base
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/") // <-- Define que o método responde a POST
    public Task create(@RequestBody Task task) { // <-- Pega o JSON do corpo da requisição
        // O controller chama o serviço para fazer o trabalho
        return this.taskService.create(task);
    }

    // Adicionar este método dentro da classe TaskController
    @GetMapping("/tasks")
    public java.util.List<Task> listAll() {
        return this.taskService.listAll();
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