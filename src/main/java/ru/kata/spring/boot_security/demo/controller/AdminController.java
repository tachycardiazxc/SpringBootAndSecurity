package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        model.addAttribute("currentUser", user);
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("existingRoles", roleService.getAllRoles());
        return "admin_test";
    }

//    @GetMapping("/create")
//    public String createUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("existing_roles", roleService.getAllRoles());
//        return "admin_create";
//    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin";
    }

//    @GetMapping("/edit/{id}")
//    public String edit(Model model, @PathVariable("id") long id) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("existing_roles", roleService.getAllRoles());
//        return "edit";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
