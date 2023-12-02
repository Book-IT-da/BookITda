package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Book;
import model.RecommBook;
import model.dao.BookDAO;

public class BookManager {
	private static BookManager bookM = new BookManager();
	private BookDAO bookDAO;

	private BookManager() {
		try {
			bookDAO = new BookDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BookManager getInstance() {
		return bookM;
	}

	// 책 생성
	public int createBook(Book book) throws SQLException {
		return bookDAO.createBook(book);
	}

	// 책 수정
	public int updateBook(Book book) throws SQLException {
		return bookDAO.ModifyBook(book);
	}

	// 책 검색
	public List<Book> findBook(String searchText, String searchType) throws SQLException {
		List<Book> books = null;
		if (searchType.equals("titleType")) {
			books = bookDAO.findBookByTitle(searchText);
		} else if (searchType.equals("authorType")) {
			books = bookDAO.findBookByAuthor(searchText);
		} else if (searchType.equals("publisherType")) {
			books = bookDAO.findBookByPublisher(searchText);
		} else if (searchType.equals("keywordType")) {
			books = bookDAO.findBookByKeyword(searchText);
		}

		// book이 null인지 검사
		if (books == null) {
			System.out.println(searchText);
			System.out.println("book은 null이다.");
			System.out.println(searchType);
		}

		return books;
	}

	// 랜덤 리스트
	public List<Book> findRandomBook() throws SQLException {
		List<Book> books = null;

		books = bookDAO.findRandomBook();

		if (books == null) {
			System.out.println("book은 null이다.");
		}

		return books;
	}

	public Book findBookInfo() throws SQLException {
		Book book = null;

		book = bookDAO.findBookInfo(null);
		if (book == null) {
			System.out.println("book은 null이다.");
		}

		return book;
	}

	public int deleteBook(String ISBN) throws SQLException {
		return bookDAO.removeBook(ISBN);
	}

	//책 세부 정보
	public Book findBookInfo(String ISBN) throws SQLException{
			Book book = bookDAO.findBookInfo(ISBN);
			
			if(book == null) {
				System.out.println("book은 null이다.");
			}
			
			return book;
		}


		public List<RecommBook> findRecommList(String userId) throws SQLException{
		    List<RecommBook> recommBooks = bookDAO.findRecommList(userId);
		    return recommBooks;
		}
}
