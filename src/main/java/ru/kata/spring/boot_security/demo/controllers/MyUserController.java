package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;

@Controller
public class MyUserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/index")
    public String viewIndexPage() {;
        return "index";
    }

    @RequestMapping("/user")
    public String viewUserPage() {;
        return "user";
    }
    @RequestMapping("/admin")
    public String viewAdminPage() {;
        return "admin";
    }








//        @GetMapping("/")
//        public String showAllUsers(Model model) {
//            List<User> allUsers = userService.getAllUsers();
//            model.addAttribute("allUsrs", allUsers);
//            return "all-users";
//        }
//        @GetMapping("/addNewUser")
//        public String addNewUser(Model model){
//            User user = new User();
//            model.addAttribute("user", user);
//
//            return "user-info";
//        }
//        @PostMapping("/saveUser")
//        public String saveUser(@ModelAttribute("user") User user) {
//            userService.saveUser(user);
//
//            return "redirect:/";
//        }
//
//        @GetMapping("/updateInfo")
//        public String updateUser(@RequestParam("usrId") int id, Model model) {
//            User user = userService.getUser(id);
//            model.addAttribute("user", user);
//
//            return "user-info";
//        }
//        @GetMapping("/deleteUser")
//        public String deleteUser(@RequestParam("usrId") int id){
//            userService.deleteUser(id);
//            return "redirect:/";
//        }


}
