package com.LibrarymanagementSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.LibrarymanagementSystem.model.Book;

@Repository
public interface LibraryRepository extends MongoRepository<Book, Integer> {
	
	public Optional<Book> findByIsbn(String isbn);
	
	public Optional<List<Book>> findByTitle(String title);

    public Optional<List<Book>> findByAuthor(String author);
    
    public Optional<List<Book>> findByAvailable(boolean avail);


}
