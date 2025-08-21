package br.com.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody User user) {
        var userCreated = this.userService.create(user);

        // Vamos adicionar uma verificação para o caso de usuário já existente
        if(userCreated == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}