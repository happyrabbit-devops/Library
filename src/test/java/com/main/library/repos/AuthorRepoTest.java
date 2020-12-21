package com.main.library.repos;

import com.main.library.domain.Author;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
public class AuthorRepoTest {

    @Autowired private AuthorRepo authorRepo;

    @Test
    public void authorFindByAlias() {

        Author author = authorRepo.findByAlias("S.King");

        assertThat(author).isNotNull();
    }

}
