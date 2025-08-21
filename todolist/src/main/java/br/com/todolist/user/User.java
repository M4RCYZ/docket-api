// Local: src/main/java/br/com/todolist/user/User.java

package br.com.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails; // Importe

import java.time.LocalDateTime;
import java.util.Collection; // Importe
import java.util.Collections;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class User implements UserDetails { // <-- MUDANÇA IMPORTANTE AQUI

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Métodos obrigatórios da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Em vez de retornar null, retornamos uma lista vazia.
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.username; // O Spring Security usará este método
    }

    @Override
    public String getPassword() {
        return this.password; // E este método
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}