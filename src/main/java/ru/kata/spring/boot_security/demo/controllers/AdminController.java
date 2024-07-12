package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/user/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "oneuser";
    }
    @GetMapping(value = "/admin")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "all-users2";
    }
    @DeleteMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

//    @GetMapping(value = "/editUser/{id}")
//    public String editUser(@PathVariable("id") int id, Model model) {
//        User user = userService.getById(id);
//        model.addAttribute("user", user);
//        return "edit_page";
//    }

    @PatchMapping(value = "/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam ArrayList<String> listRoleId) {
        Set<Role> userRole = new HashSet<>();
        for (String roleId : listRoleId) {
            Role role = roleService.getById(Long.parseLong(roleId));
            userRole.add(role);
        }
        user.setRoleSet(userRole);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/createUser")
    public String createUser(@ModelAttribute("user") User user, @RequestParam ArrayList<String> listRoleId) {
        Set<Role> userRole = new HashSet<>();
        for (String roleId : listRoleId) {
            Role role = roleService.getById(Long.parseLong(roleId));
            userRole.add(role);
        }
        user.setRoleSet(userRole);
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/oneuser")
    public String oneUser(@AuthenticationPrincipal User activeUser, Model model) {
        model.addAttribute("roles", activeUser.getRoleSet());
        model.addAttribute("user", activeUser);
        return "oneuser";
    }

//    @RequestMapping(value = "/newUser")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "new_user";
//    }


}
