package ru.itmentor.spring.boot_security.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.*;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   @Column
   private String password;
    @Column
    private String email;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
   @Enumerated(EnumType.STRING)
    @Fetch(FetchMode.JOIN) // Используйте FetchType.EAGER с FetchMode.JOIN
    private Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
