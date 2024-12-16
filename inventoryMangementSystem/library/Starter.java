package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;

/**
 * 
 * @author jeyanthkumar
 * 
 * It is library inventory management system.
 * System allows users(librarian/borrower) to login
 * System has some of the book in the library they are os, data structure, java programming, maths,..etc
 * System allows a librarian to add book.
 * System allows a librarian to know the total available books details & count of total books
 * System allows a borrower can search a book
 * System allows a borrower can purchase a book
 * 
 * Librarian login user name is "jeyanth" password is "jeyanth@admin"
 * Borrower1 login user name is "user1" password is "user1@borrower"
 * Borrower2 login user name is "user2" password is "user2@borrower"
 * Borrower3 login user name is "user3" password is "user3@borrower"
 * Borrower4 login user name is "user4" password is "user4@borrower"
 * Borrower5 login user name is "user5" password is "user5@borrower"
 *
 */
public class Starter {
	static List<Book> books = getBookData();
	static List<User> users = getUserData();
	static int loginAttempt = 0;

	public static void main(String[] args) {

		System.out.println("*****************Welcome to Library Inventory Management System*******************");
		librarySystem();
		System.out.println("*****************You have sucessfully Logged Out!!!!*******************");
	}

	private static void librarySystem() {
		
		Integer loggedInUserId = userLogin();

		if(loggedInUserId==1) {
			System.out.println("Successfully logged in as Librarian user");

			System.out.println("Books details...");
			System.out.println("Number of available books: "+books.stream().map(b->b.noOfBooks).reduce(0, Integer::sum));
			System.out.println(books.toString());
			System.out.println(new JSONArray(books.toString()).toString(4));

			Scanner sc= new Scanner(System.in);
			System.out.print("Do you Want to add book enter(yes/no): ");
			String addBook= sc.nextLine();
			if(addBook.equals("yes")) {
				System.out.print("Enter book name: ");
				String bookName= sc.nextLine();
				System.out.print("Enter author name: ");
				String authorName= sc.nextLine();
				System.out.print("Enter published date: ");
				String publishedDate= sc.nextLine();
				System.out.print("Enter book count: ");
				String count= sc.nextLine();

				Integer maxBookId = books.stream().map(b->b.getId()).reduce(0, Integer::max);

				Book newBook = new Book(maxBookId+1, bookName, authorName, publishedDate, Integer.parseInt(count), new HashMap<Integer, String>());
				books.add(newBook);
				System.out.println("Book successfully added into library system !!!!");
			}
			librarySystem();

		}else if(loggedInUserId!=0 && loggedInUserId!=-1) {
			System.out.println("Successfully logged in as Borrower user");

			searchBook();

			Scanner sc= new Scanner(System.in);
			System.out.print("Enter book name to borrow: ");
			String bookName= sc.nextLine();

			Book borrowedBook = books.stream().filter(b->b.getBookName().equals(bookName)).findFirst().get();
			books.remove(borrowedBook);

			borrowedBook.setNoOfBooks(borrowedBook.getNoOfBooks()-1);
			HashMap<Integer,String> taggedUserhm = borrowedBook.getTaggedUserList();
			taggedUserhm.put(loggedInUserId, users.stream().filter(u->u.getUserId().equals(loggedInUserId)).findFirst().get().getUserName());
			borrowedBook.setTaggedUserList(taggedUserhm);

			books.add(borrowedBook);

			System.out.println("You have successfully borrowed: [Book: "+bookName+"] ");
			librarySystem();
		}

	}

	private static void searchBook() {
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter book name to search: ");
		String searchBookName= sc.nextLine();

		System.out.println("<<<<<<<<<<<Search result>>>>>>>>>>>");
		List<String> lbook = books.stream().filter(b->b.getNoOfBooks()>0).filter(b->b.getBookName().contains(searchBookName) && b.getNoOfBooks()>0).map(b->b.getBookName()).collect(Collectors.toList());

		if(lbook.size()<=0) {
			System.out.println("Not found any result... Try again with different keyword!!!");
			searchBook();
		}else {
			System.out.println(String.join("\n", lbook));
		}

	}

	private static Integer userLogin() {
		Integer loggedInUserId = login();

		if(loggedInUserId==0) {
			if(loginAttempt > 3) {
				System.out.println("Login Attempt exceeded...");
				System.out.println("*************Bye*****************");
			}else {
				loginAttempt++;
				loggedInUserId = userLogin();
			}
		}
		return loggedInUserId;
	}

	private static Integer login() {

		Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
		System.out.print("Enter user type(librarian or borrower) OR Enter (logout) to exit : ");
		String userType= sc.nextLine();

		Integer loggedInUserId = 0;

		if(userType.equals("logout")) {
			loggedInUserId = -1;
			return loggedInUserId;
		}

		System.out.print("Enter username: ");
		String userName= sc.nextLine();
		System.out.print("Enter password: ");
		String password= sc.nextLine();



		try {

			loggedInUserId = new Integer(users.stream()
					.filter(u->u.getUserType().equals(userType) && u.getUserName().equals(userName) && u.getPassword().equals(password))
					.mapToInt(u->u.getUserId()).findFirst().getAsInt());
		}catch(Exception ex) {
			System.out.println("Invalid user credentials!!!!");
		}
		return loggedInUserId;
	}

	private static List<User> getUserData() {
		User u1 = new User(1, "jeyanth", "librarian", "jeyanth@admin");
		User u2 = new User(2, "user1", "borrower", "user1@borrower");
		User u3 = new User(3, "user2", "borrower", "user2@borrower");
		User u4 = new User(4, "user3", "borrower", "user3@borrower");
		User u5 = new User(5, "user4", "borrower", "user4@borrower");
		User u6 = new User(6, "user5", "borrower", "user5@borrower");

		List<User> userList = new ArrayList<User>();
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		userList.add(u4);
		userList.add(u5);
		userList.add(u6);

		return userList;
	}

	private static List<Book> getBookData() {
		Book b1 = new Book(1, "os", "author1", "2020-01-01", 5, new HashMap<Integer,String>());
		Book b2 = new Book(2, "maths", "author2", "2021-04-01", 2,new HashMap<Integer,String>());
		Book b3 = new Book(3, "database mangement", "author3", "2020-07-05", 4,  new HashMap<Integer,String>());
		Book b4 = new Book(4, "networking", "author4", "2022-04-01", 5,new HashMap<Integer,String>());
		Book b5 = new Book(5, "data structure", "author5", "2022-06-06", 8,new HashMap<Integer,String>());
		Book b6 = new Book(6, "java programming", "author6", "2022-08-01", 10,new HashMap<Integer,String>());
		Book b7 = new Book(7, "os", "author7", "2021-01-01", 5, new HashMap<Integer,String>());
		Book b8 = new Book(1, "os", "author1", "2020-01-01", 5, new HashMap<Integer,String>());

		List<Book> bookList = new ArrayList<Book>();
		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
		bookList.add(b5);
		bookList.add(b6);
		bookList.add(b7);
		bookList.add(b8);

		return bookList;
	}

}
