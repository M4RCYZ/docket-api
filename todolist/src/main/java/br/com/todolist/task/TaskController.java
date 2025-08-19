// Local: src/main/java/br/com/todolist/task/TaskController.java

package br.com.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // <-- Importante

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
}