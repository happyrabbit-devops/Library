package com.main.library.controller.impl;

import com.main.library.controller.IUserController;
import com.main.library.domain.Role;
import com.main.library.domain.User;
import com.main.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController implements IUserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "usrList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String getUserEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "usrEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email) {

        userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }

    @GetMapping("subscribe/{user}")
    public String getSubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user) {
        userService.subscribe(currentUser, user);
        return "redirect:/user-comments/" + user.getId();
    }

    @GetMapping("unsubscribe/{user}")
    public String getUnsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user) {
        userService.unsubscribe(currentUser, user);
        return "redirect:/user-comments/" + user.getId();
    }

    @GetMapping("{type}/{user}/list")
    public String getUserList(
            Model model,
            @PathVariable User user,
            @PathVariable String type) {

        model.addAttribute("userChannel", user);
        model.addAttribute("type", "subscriptions".equals(type) ? "Подписки" : "Читатели");

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }

        return "subs";
    }
}
