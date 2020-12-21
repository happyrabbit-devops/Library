package com.main.library.repos;

import com.main.library.domain.Author;
import com.main.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    Author findByUser(User user);

    Author findByAlias(String alias);
}

