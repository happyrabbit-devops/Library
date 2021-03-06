package com.main.library.repos;

import com.main.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findByCaption(String caption);

}
