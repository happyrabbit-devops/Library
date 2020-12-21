package com.main.library.service;

import com.main.library.domain.Book;
import com.main.library.domain.Genre;
import com.main.library.domain.User;
import com.main.library.repos.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.main.library.repos.BookRepo;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    public boolean addBook(Book book, User user, Map<String, String> form) {
        List<Book> bookFromDb = bookRepo.findByCaption(book.getCaption());

        if (bookFromDb.size() > 0) {
            return false;
        }

        System.out.println(book.getGenres());

        Set<String> genres = Arrays.stream(Genre.values())
                .map(Genre::name)
                .collect(Collectors.toSet());

        Set<Genre> myGenres = new HashSet<>();

        for (String key : form.keySet()) {
            if (genres.contains(key)) {
                myGenres.add(Genre.valueOf(key));
            }
        }

        book.setGenres(myGenres);

        book.setAuthor(authorRepo.findByUser(user));

        bookRepo.save(book);

        return true;
    }
}