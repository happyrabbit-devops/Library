package com.main.library.controller.impl;

import com.main.library.controller.IMainController;
import com.main.library.domain.*;
import com.main.library.repos.BookRepo;
import com.main.library.repos.CommentRepo;
import com.main.library.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Controller
public class MainController implements IMainController {

    private final CommentRepo commentRepo;
    private final BookRepo bookRepo;
    private final IBookService bookService;

    @Autowired
    public MainController(CommentRepo commentRepo, BookRepo bookRepo, IBookService bookService) {
        this.commentRepo = commentRepo;
        this.bookRepo = bookRepo;
        this.bookService = bookService;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String start(Map<String, Object> model) {
        return "start";
    }

    @GetMapping("/books")
    public String getBooks(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        Iterable<Book> books;

        if (filter != null && !filter.isEmpty()) {
            books = bookRepo.findByCaption(filter);
        } else {
            books = bookRepo.findAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        model.addAttribute("genres", Genre.values());

        return "books";
    }

    @PostMapping("/books")
    public String addBook(@AuthenticationPrincipal User user,
                          @Valid Book book,
                          BindingResult bindingResult,
                          Model model,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam("authorAlias") String authorAlias,
                          @RequestParam Map<String, String> form) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String,
                    String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("book", book);
        } else {

            saveFile(null, book, file);

            model.addAttribute("book", null);

            bookService.addBook(book, authorAlias, form);
        }

        Iterable<Book> books = bookRepo.findAll();

        model.addAttribute("books", books);

        return "redirect:/books";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        Iterable<Comment> comments;

        if (filter != null && !filter.isEmpty()) {
            comments = commentRepo.findByTag(filter);
        } else {
            comments = commentRepo.findAll();
        }

        model.addAttribute("comments", comments);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String addComment(@AuthenticationPrincipal User user,
                             @Valid Comment comment,
                             BindingResult bindingResult,
                             Model model, @RequestParam("file") MultipartFile file) throws IOException {
        comment.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String,
                    String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("comment", comment);
        } else {

            saveFile(comment, null, file);

            model.addAttribute("comment", null);

            commentRepo.save(comment);
        }

        Iterable<Comment> comments = commentRepo.findAll();

        model.addAttribute("comments", comments);

        return "main";
    }

    private void saveFile(@Valid Comment comment, @Valid Book book, @RequestParam("file") MultipartFile file) throws IOException {

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                final boolean mkdir = uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            if (comment != null) comment.setFilename(resultFilename);
            if (book != null) book.setFilename(resultFilename);
        }
    }

    @GetMapping("/books/{book}")
    public String getBookDetails(@AuthenticationPrincipal User currentUser, @PathVariable Book book, Model model) {

        Set<Comment> comments = book.getComments();
        Set<Genre> genres = book.getGenres();

        model.addAttribute("book", book);
        model.addAttribute("bookGenres", genres);
        model.addAttribute("comments", comments);

        return "bookDetails";
    }

    @PostMapping("/books/{book}")
    public String updateBookComment(@AuthenticationPrincipal User currentUser,
                                    @Valid Comment comment,
                                    @PathVariable Long book,
                                    @RequestParam("file") MultipartFile file) throws IOException {

        comment.setAuthor(currentUser);
        saveFile(comment, null, file);
        commentRepo.save(comment);

        return "redirect:/books/" + book;
    }

    @GetMapping("/user-comments/{user}")
    public String getUserComments(@AuthenticationPrincipal User currentUser,
                                  @PathVariable User user,
                                  Model model,
                                  @RequestParam(required = false) Comment comment) {

        Set<Comment> comments = user.getComments();

        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("comments", comments);
        model.addAttribute("comment", comment);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "comments";
    }

    @PostMapping("/user-comments/{user}")
    public String updateUserComment(@AuthenticationPrincipal User currentUser,
                                    @PathVariable Long user,
                                    @RequestParam("id") Comment comment,
                                    @RequestParam("text") String text,
                                    @RequestParam("tag") String tag,
                                    @RequestParam("file") MultipartFile file) throws IOException {

        if (comment != null && comment.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                comment.setText(text);
            }
            if (!StringUtils.isEmpty(tag)) {
                comment.setTag(tag);
            }

            saveFile(comment, null, file);

            commentRepo.save(comment);
        }
        return "redirect:/user-comments/" + user;
    }
}
