package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

public class ListBookController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("listBook controlloer OK");
		BookManager manager = BookManager.getInstance();
		String searchText = request.getParameter("searchText");

		List<Book> books = null;
		books = manager.findBook(searchText);

		request.setAttribute("books", books);

		return "/book/searchList.jsp";
	}

}
