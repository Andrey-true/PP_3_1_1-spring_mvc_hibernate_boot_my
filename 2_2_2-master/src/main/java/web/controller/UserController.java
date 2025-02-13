package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import jakarta.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/usersTable")
    public String printUsers(ModelMap model) {
        List<User> users = userService.getUsers(Integer.MAX_VALUE);
        model.addAttribute("users", users);
        return "usersTable";
    }

    @PostMapping(value = "/usersTable/add")
    public String addUser(@RequestParam("name") String name, @RequestParam("age") int age,
                          @RequestParam("city") String city) {
        userService.addUser(new User(name, age, city));
        return "redirect:/usersTable";
    }

    @GetMapping ("/usersTable/showEditForm")
    public String showEditForm(@RequestParam("id") Long id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "edit";
    }


    @PostMapping("/usersTable/update")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "fix-form";
        }
        userService.updateUser(id, user);
        return "redirect:/usersTable";
    }

    @PostMapping("/usersTable/remove")
    public String removeUser(@RequestParam("id") Long id) {
        userService.removeUser(id);
        System.out.println(id);
        return "redirect:/usersTable";
    }

}