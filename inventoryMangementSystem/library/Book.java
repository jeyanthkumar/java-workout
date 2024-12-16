package library;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Book {
	
	Integer id;
	String bookName;
	String authorName;
	String publishDate;
	Integer noOfBooks;
	HashMap<Integer, String> taggedUserList;
	
	public Book(Integer id, String bookName, String authorName, String publishDate, Integer noOfBooks,
			HashMap<Integer, String> taggedUserList) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.publishDate = publishDate;
		this.noOfBooks = noOfBooks;
		this.taggedUserList = taggedUserList;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getNoOfBooks() {
		return noOfBooks;
	}

	public void setNoOfBooks(Integer noOfBooks) {
		this.noOfBooks = noOfBooks;
	}

	public HashMap<Integer, String> getTaggedUserList() {
		return taggedUserList;
	}

	public void setTaggedUserList(HashMap<Integer, String> taggedUserList) {
		this.taggedUserList = taggedUserList;
	}


	@Override
	public int hashCode() {
		return Objects.hash(authorName, bookName, id, noOfBooks, publishDate, taggedUserList);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authorName, other.authorName) && Objects.equals(bookName, other.bookName)
				&& Objects.equals(id, other.id) && Objects.equals(noOfBooks, other.noOfBooks)
				&& Objects.equals(publishDate, other.publishDate)
				&& Objects.equals(taggedUserList, other.taggedUserList);
	}


	@Override
	public String toString() {
		return "{\"id\":\""+id+"\", \"bookName\": \""+bookName+"\", \"authorName\": \""+authorName+"\", \"publishDate\": \""+publishDate+"\", \"noOfBooks\": \""+noOfBooks+"\", \"taggedUserList\": \""+taggedUserList+"\"}";
	}
	
	
	
}
