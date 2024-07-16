package com.tccompany.tciambackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @Column(name = "id_users", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_has_role",
            joinColumns = @JoinColumn(name = "id_users"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> userRoles;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "date_of_birthday", nullable = false)
    private LocalDate dateOfBirthday;

    @Column(name = "gender", nullable = false, length = 45)
    private String gender;


    @Column(name = "phone_number", nullable = false, length = 45, unique = true)
    private String phoneNumber;


    @Column(name = "name", nullable = false, length = 45)
    private String name;


    @Column(name = "surname", nullable = false, length = 45)
    private String surname;


    @Column(name = "email", nullable = false, length = 45, unique = true)
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles;
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