package com.main.library.service;

import com.main.library.domain.Book;
import com.main.library.domain.User;

import java.util.Map;

public interface IBookService {

    boolean addBook(Book book, String authorAlias, Map<String, String> form);
}
