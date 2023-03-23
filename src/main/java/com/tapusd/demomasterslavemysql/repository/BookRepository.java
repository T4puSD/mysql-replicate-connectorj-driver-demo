package com.tapusd.demomasterslavemysql.repository;

import com.tapusd.demomasterslavemysql.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
