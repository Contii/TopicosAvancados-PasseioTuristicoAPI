package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// ou somente @Data

//jakarta - JPA
@Entity
@Table(name = "TB_Pessoa") // caso nao inserir ele coloca o nome da classe
public class Person extends BaseEntity implements UserDetails {
   
    @Column(name = "name", length = 140, nullable = false)
    private String nome;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", length = 50, nullable = false)
    private String senha;

    @Column(name = "phone", length = 20, nullable = true)
    private String telefone;

    @Column(name = "birth", nullable = true)
    private LocalDateTime nascimento;

    @Column(name = "perfil", length = 20, nullable = false) // Cliente ou Agencia
    private String perfil;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();

    
    
    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    public List<String> getRoles() {
        return getAuthorities().stream().map(e -> e.getAuthority()).toList();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }
}