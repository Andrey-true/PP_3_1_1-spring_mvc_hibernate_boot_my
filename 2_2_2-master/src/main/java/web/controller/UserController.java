package web.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/usersTable") // Вынесли общий путь
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class); // Логгер

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String printUsers(ModelMap model) {
        List<User> users = userService.getUsers(Integer.MAX_VALUE);
        model.addAttribute("users", users);
        return "usersTable";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("age") int age,
                          @RequestParam("city") String city) {
        userService.addUser(new User(name, age, city));
        return "redirect:/usersTable";
    }

    @GetMapping("/showEditForm")
    public String showEditForm(@RequestParam("id") Long id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @RequestParam("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "fix-form";
        }
        userService.updateUser(id, user);
        return "redirect:/usersTable";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam("id") Long id) {
        logger.info("Removing user with ID: {}", id); // Вместо System.out.println
        userService.removeUser(id);
        return "redirect:/usersTable";
    }
}
