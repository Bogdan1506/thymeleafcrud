package com.thymeleafcrud.controller;

import com.thymeleafcrud.entity.User;
import com.thymeleafcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showIndexPage(Model model) {
        List<User> list = userService.findAll();
        User user = new User();
        model.addAttribute("list", list);
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/new")
    public String createNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user_form";
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "edit_user_form";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(userService.findById(id));
        return "redirect:/";
    }

    @RequestMapping("/get")
    public String getById(@ModelAttribute("user") User user, Model model) {
        User new_user = userService.findById(user.getId());
        model.addAttribute("user", new_user);
        return "edit_user_form";
    }
}
