package com.main.library.controller;

import com.main.library.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface IUserController {

    String getUserList(Model model);

    String getUserEditForm(@PathVariable User user, Model model);

    String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user);

    String getProfile(Model model, @AuthenticationPrincipal User user);

    String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email);

    String getSubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user);

    String getUnsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user);

    String getUserList(
            Model model,
            @PathVariable User user,
            @PathVariable String type);

}
