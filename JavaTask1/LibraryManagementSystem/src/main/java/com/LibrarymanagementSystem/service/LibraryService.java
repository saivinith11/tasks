package com.LibrarymanagementSystem.service;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.LibrarymanagementSystem.dao.LibraryRepository;
import com.LibrarymanagementSystem.model.Book;
import com.LibrarymanagementSystem.model.DatabaseSequence;
import com.LibrarymanagementSystem.model.Response;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class LibraryService {
	
	@Autowired
	private LibraryRepository bookRepo;
	
	public Response addBook(Book req)
    {
        Response response=new Response();
        Optional<Book> book=bookRepo.findByIsbn(req.getIsbn());
        if(book.isPresent())
        {
            response.setStatus("Failed");
            response.setMessage("Book already exists");
            return response;
        }
        else {
            bookRepo.save(req);
            response.setStatus("Success");
            response.setMessage("Book added successfully");
            response.setBookDetails(req);
            return response;

        }
        
    }
	public Response removeBook(String isbn)
    {
        Response response=new Response();
        Optional<Book> book=bookRepo.findByIsbn(isbn);
        if(book.isPresent())
        {
            bookRepo.deleteById(book.get().getId());
            response.setStatus("Success");
            response.setMessage("Book removed successfully");
            return response;
        }
        else {
            response.setStatus("Failed");
            response.setMessage("Book doesnot  exists");
            return response;
        }
    }
	
	public Response findByTitle(String title)
    {
        Response response=new Response();
        Optional<List<Book>> list=bookRepo.findByTitle(title);
        if(list.isPresent() && !list.get().isEmpty())
        {
            response.setStatus("Success");
            response.setMessage("Book retrieved successfully");
            response.setAllBooks(list.get());
            return response;
        }
        else {
            response.setStatus("Failure");
            response.setMessage("no books found by this title");
            return response;
        }
    }
	
	public Response findByAuthor(String author) {
        Response response = new Response();
        Optional<List<Book>> list = bookRepo.findByAuthor(author);
        if (list.isPresent() && !list.get().isEmpty()) {
            response.setStatus("Success");
            response.setMessage("Books retrieved successfully");
            response.setAllBooks(list.get());
            return response;
        } else {
            response.setStatus("Failure");
            response.setMessage("No books found");
            return response;
        }
	}
	
	public Response findAllBooks() {
        Response response = new Response();
        List<Book> list = bookRepo.findAll();
        if (!list.isEmpty()) {
            response.setStatus("Success");
            response.setMessage("Books retrieved successfully");
            response.setAllBooks(list);
            return response;
        } else {
            response.setStatus("Failure");
            response.setMessage("No record found");
            return response;
        }
    }
	
	public Response findAvailableBooks() {
        Response response = new Response();
        Optional<List<Book>> list = bookRepo.findByAvailable(true);
        if (list.isPresent() && !list.get().isEmpty()) {
            response.setStatus("Success");
            response.setMessage("Books retrieved successfully");
            response.setAllBooks(list.get());
            return response;
        } else {
            response.setStatus("Failure");
            response.setMessage("No books are available");
            return response;
        }
	}
	@Autowired
	private MongoOperations mongoOperations;



	public long getSequenceNumber(String sequenceName) {
	//get sequence no
	Query query = new Query(Criteria.where("id").is(sequenceName));
	//update the sequence no
	Update update = new Update().inc("seq", 1);
	//modify in document
	DatabaseSequence counter = mongoOperations.findAndModify(query,
	update, options().returnNew(true).upsert(true),
	DatabaseSequence.class);



	return !Objects.isNull(counter) ? counter.getSeq() : 1;



	}

}
