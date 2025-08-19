// Local: src/main/java/br/com/todolist/task/TaskService.java

package br.com.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // <-- A única anotação de componente necessária aqui
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // O método não lida com HTTP, apenas recebe o objeto e trabalha com ele.
    public Task create(Task task) {
        return this.taskRepository.save(task);
    }
}