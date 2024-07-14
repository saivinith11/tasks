package com.LibrarymanagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibrarymanagementSystem.model.Book;
import com.LibrarymanagementSystem.model.Response;
import com.LibrarymanagementSystem.service.LibraryService;

@RestController
@RequestMapping("/LMS")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryservice;
	
	@PostMapping("/addBook")
    public Response addBook(@RequestBody Book req)
    {
        return libraryservice.addBook(req);
    }
	
	@PostMapping("/removeBook")
    public Response addBook(@RequestBody String isbn)
    {
        return libraryservice.removeBook(isbn);
    }

	@PostMapping("/getBookByTitle")
    public Response getAllBook(@RequestBody String title)
    {
        return libraryservice.findByTitle(title);
    }
	
	@PostMapping("/getBookByAuthor")
    public Response getAllBookByAuthor(@RequestBody String author)
    {
        return libraryservice.findByAuthor(author);
    }
	
	@GetMapping("/getAllBooks")
    public Response getAllBooks() {
        return libraryservice.findAllBooks();
    }
	
	@GetMapping("/getAvailableBooks")
    public Response getAvailableBooks() {
        return libraryservice.findAvailableBooks();
    }
}
