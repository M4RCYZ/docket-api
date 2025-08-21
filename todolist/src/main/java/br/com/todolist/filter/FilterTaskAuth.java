// Local: src/main/java/br/com/todolist/filter/FilterTaskAuth.java

package br.com.todolist.filter;

import br.com.todolist.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Pega o caminho da requisição (ex: /tasks/, /users/)
        var servletPath = request.getServletPath();

        // **ESTA É A MUDANÇA PRINCIPAL**
        // Se a rota for de tarefas, executa a autenticação
        if (servletPath.startsWith("/tasks")) {
            // 1. Pegar a autorização (usuário e senha)
            var authorization = request.getHeader("Authorization");

            if (authorization == null) {
                response.sendError(401);
                return;
            }

            // Decodifica de Base64
            var authDecoded = Base64.getDecoder().decode(authorization.substring("Basic ".length()));
            String[] credentials = new String(authDecoded).split(":");
            String username = credentials[0];
            String password = credentials[1];

            // 2. Validar o usuário
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401, "Usuário sem autorização");
                return;
            }

            // 3. Validar a senha
            var passwordMatches = this.passwordEncoder.matches(password, user.getPassword());
            if (!passwordMatches) {
                response.sendError(401, "Senha inválida");
                return;
            }

            // 4. Se tudo estiver certo, adiciona o usuário na requisição e segue em frente
            request.setAttribute("user", user);
            filterChain.doFilter(request, response);

        } else {
            // Se a rota não for de tarefas, apenas continua o fluxo
            filterChain.doFilter(request, response);
        }
    }
}