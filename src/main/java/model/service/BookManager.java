package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Book;
import model.dao.BookDAO;

public class BookManager {
	private static BookManager bookM = new BookManager();
	private BookDAO bookDAO;
	
	private BookManager() {
		try {
			bookDAO = new BookDAO();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BookManager getInstance() {
		return bookM;
	}
	
	public int create(Book book) throws SQLException{
		return bookDAO.createBook(book);
	}
	
	public int update(Book book) throws SQLException{
		return bookDAO.ModifyBook(book);
	}
	
	//�˻����� �������� �˻� ��� �����ؾ���. �ϴ� �⺻�� title�� ã��
	public List<Book> findBook(String title) throws SQLException{
		List<Book> books = bookDAO.findBookByTitle(title);
		
		if(books == null) {
			System.out.println(title);
			System.out.println("book�� null�̴�.");
		}
		
		return books;
	}
	
	public int dekete(String ISBN) throws SQLException{
		return bookDAO.removeBook(ISBN);
	}
	
	//���� ���� ��������
	//�˻����� �������� �˻� ��� �����ؾ���. �ϴ� �⺻�� title�� ã��
		public Book findBookInfo(String ISBN) throws SQLException{
			Book book = bookDAO.findBookInfo(ISBN);
			
			if(book == null) {
				System.out.println("book�� �������� �ʾ� ������ �� ����");
			}
			
			return book;
		}
		
}
