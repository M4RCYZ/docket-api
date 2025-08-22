package br.com.todolist.task;

import br.com.todolist.user.User;
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
    // O método create agora recebe o DTO e o User
    public Task create(TaskCreateDTO taskDTO, User user) {
        Task newTask = new Task();
        // Copiamos os dados do DTO para a nova entidade
        newTask.setTitle(taskDTO.getTitle());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setStartAt(taskDTO.getStartAt());
        newTask.setEndAt(taskDTO.getEndAt());
        newTask.setPriority(taskDTO.getPriority());

        // Associamos o usuário logado
        newTask.setUser(user);

        return this.taskRepository.save(newTask);
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