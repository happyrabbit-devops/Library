package com.main.library.controller;

import com.main.library.domain.Book;
import com.main.library.domain.Comment;
import com.main.library.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

public interface IMainController {

    String start(Map<String, Object> model);

    String getBooks(@RequestParam(required = false, defaultValue = "") String filter, Model model);

    String addBook(@AuthenticationPrincipal User user,
            @Valid Book book,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @RequestParam Map<String, String> form) throws IOException;

    String main(@RequestParam(required = false, defaultValue = "") String filter, Model model);

    String addComment(@AuthenticationPrincipal User user,
                      @Valid Comment comment,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException;

    String getBookDetails(@AuthenticationPrincipal User currentUser, @PathVariable Book book, Model model);

    String updateBookComment(@AuthenticationPrincipal User currentUser,
                             @Valid Comment comment,
                             @PathVariable Long book,
                             @RequestParam("file") MultipartFile file) throws IOException;

    String getUserComments(@AuthenticationPrincipal User currentUser,
                        @PathVariable User user,
                        Model model,
                        @RequestParam(required = false) Comment comment);

    String updateUserComment(@AuthenticationPrincipal User currentUser,
                         @PathVariable Long user,
                         @RequestParam("id") Comment comment,
                         @RequestParam("text") String text,
                         @RequestParam("tag") String tag,
                         @RequestParam("file") MultipartFile file) throws IOException;


}
