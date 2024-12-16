package com.java.workout.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jeyanthkumar_S
 *  It is used for traversing through the collection of data in a particular class.
 * This pattern hides the actual implementation of traversal through the collection. The application programs just use iterator methods for different purposes. Iterator pattern allows accessing the elements of a collection object in a sequential manner without knowledge of its structure. Key points to remember about this interface are:
 * This pattern is used when you foresee a collection is traversed with different types of criteria.
 * The collection object should be accessed and traversed without exposing its data structure.
 * New traversal operations should be defined for collection object without changing its interface.
 *
 */

interface BookIterator {
	
	public boolean hasMore();
 
	public Book next();
}

class Book {
	 
	private String name;
	private String publication;
	
	public Book(String name, String publication) {
		super();
		this.name = name;
		this.publication = publication;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	
	@Override
	public String toString() {
		return "Book [name=" + name + ", publication=" + publication + "]";
	}

}
class BookService {
	
	private List<Book> books;
	
	public BookIterator getIterator(String publication) {
		return new BookIteratorImpl(books, publication);
	}
	
	private class BookIteratorImpl implements BookIterator {

		private String publicationCheck;
		private List<Book> listOfBooks;
		private int index;
		
		public BookIteratorImpl(List<Book> books, String pubCheck) {
			this.listOfBooks = books;
			this.publicationCheck = pubCheck;
		}

		@Override
		public boolean hasMore() {
			while(index < listOfBooks.size()) {
				Book buk = this.listOfBooks.get(index);
				if(buk.getPublication().equalsIgnoreCase(publicationCheck)) {
					return true;
				} else {
					index++;
				}
			}
			return false;
		}

		@Override
		public Book next() {
			Book buk = this.listOfBooks.get(index);
			index++;
			return buk;
		}
	}
	
	public BookService() {
		books = new ArrayList<Book>();
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}

public class IteratorDesignPattern {

	public static void main(String[] args) {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Java", "Pub A"));
		books.add(new Book("C++", "Pub B"));
		books.add(new Book("PHP", "Pub A"));
		books.add(new Book("Kotlin", "Pub B"));
		books.add(new Book("Kafka", "Pub A"));
		books.add(new Book("Salesforce", "Pub C"));
		
		BookService bs = new BookService();
		bs.getBooks().addAll(books);
		
		System.out.println("List of Books from Publisher C");
		
		BookIterator bi = bs.getIterator("Pub C");
		while (bi.hasMore()) {
			Book b = bi.next();
			System.out.println("--> " + b.toString());
		}
		
		System.out.println("\nList of Books from Publisher B");
		
		bi = bs.getIterator("Pub B");
		while (bi.hasMore()) {
			Book b = bi.next();
			System.out.println("--> " + b.toString());
		}
		
	}
}