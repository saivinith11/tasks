package com.LibrarymanagementSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="bookdetails")
public class Book {
	
	@Id
	private int id;
	 private String title;
	 private String author;
	 private String isbn;
	 private String genre;
	 private String publicationYear;
	 private String department;
	 private boolean available;
	 
	 
	 
	 @org.springframework.data.annotation.Transient
		public static final String SEQUENCE_NAME = "users_sequence";
		
		public static String getSequenceName() {
			return SEQUENCE_NAME;
		}
		
	
	public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	 
	 
	 
	 
	 
	 
}