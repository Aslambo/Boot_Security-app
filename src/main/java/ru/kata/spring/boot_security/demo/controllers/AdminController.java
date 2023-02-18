package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAdminUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/createNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", roleService.getListOfRoles());
        return "newUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        List<Role> listRoles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            listRoles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(listRoles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String getFormToUpdateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("listRoles", roleService.getListOfRoles());
        return "editUser";
    }

    @PatchMapping("/editUser/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        List<Role> listRoles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            listRoles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(listRoles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";

    }
}
