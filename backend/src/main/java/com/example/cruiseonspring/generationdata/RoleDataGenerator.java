package com.example.cruiseonspring.generationdata;


import com.example.cruiseonspring.entity.Role;
import com.example.cruiseonspring.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

//@Component
@RequiredArgsConstructor
public class RoleDataGenerator {
    private final RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void generate() {
        List<Role> roles = generateSampleRoles();
        System.out.println("Save Role entity: " + roles.toString());
        roleRepository.saveAll(roles);
    }

    private List<Role> generateSampleRoles() {
        return List.of(
                new Role("USER"),
                new Role("ADMIN")
        );
    }
}
