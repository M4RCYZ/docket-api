package br.com.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User create(User user) {
        // Verifica se o username já existe no banco
        var userExists = this.userRepository.findByUsername(user.getUsername());
        if (userExists != null) {
            // Se existir, retorna null para o controller tratar o erro
            return null;
        }

        // Criptografa a senha
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return this.userRepository.save(user);
    }
}