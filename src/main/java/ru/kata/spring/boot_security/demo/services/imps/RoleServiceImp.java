package ru.kata.spring.boot_security.demo.services.imps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImp(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Role> getAllRoles() {
        return repository.findAll();
    }
}
