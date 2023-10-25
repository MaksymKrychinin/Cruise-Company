package com.example.cruiseonspring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @Column(name = "id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> userRoles;

    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    @Min(value = 8, message = "Password should be at least 8 characters")
    @Column(name = "password", nullable = false)
    private String password;
    @NotNull(message = "Date of birthday should not be null")
    @Past(message = "Date of birthday should be in the past")
    @Column(name = "date_of_birthday", nullable = false)
    private LocalDate dateOfBirthday;
    @NotNull
    @Column(name = "gender", nullable = false, length = 45)
    private String gender;

    @NotNull(message = "Phone number should not be null")
    @NotEmpty(message = "Phone number should not be empty")
    @Pattern(regexp = "^[0-9]{11}$", message = "Phone number should be valid")
    @Column(name = "phone_number", nullable = false, length = 45, unique = true)
    private String phoneNumber;

    @NotNull(message = "Name should not be null")
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @NotNull(message = "Surname should not be null")
    @NotEmpty(message = "Surname should not be empty")
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Email(message = "Email should be valid")
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