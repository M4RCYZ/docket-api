package br.com.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service // <-- A única anotação de componente necessária aqui
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // O método não lida com HTTP, apenas recebe o objeto e trabalha com ele.
    public Task create(Task task) {
        return this.taskRepository.save(task);
    }

    // Adicionar este método dentro da classe TaskService
    public java.util.List<Task> listAll() {
        return this.taskRepository.findAll();
    }

    public Task update(UUID id, Task taskDetails) {
        taskDetails.setId(id);
        return this.taskRepository.save(taskDetails);
    }

    // Adicionar este método dentro da classe TaskService
    public void delete(UUID id) {
        this.taskRepository.deleteById(id);
    }

    // Adicione este método dentro da classe TaskService
    public List<TaskDTO> list(UUID userId) {
        List<Task> tasks = this.taskRepository.findByUserId(userId);
        // Usamos o Stream API do Java para converter cada Task em um TaskDTO
        return tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
    }

}