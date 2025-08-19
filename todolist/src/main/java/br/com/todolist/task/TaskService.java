package br.com.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    // Adicionar este método dentro da classe TaskService
    public void delete(UUID id) {
        this.taskRepository.deleteById(id);
    }

}