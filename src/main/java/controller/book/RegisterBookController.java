package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

//책을 추가하기 위한 컨트롤러
public class RegisterBookController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		System.out.println("REGISTERbOOKController OK");
		BookManager manager = BookManager.getInstance();

		Book book = new Book(request.getParameter("ISBN"), request.getParameter("title"),
				request.getParameter("author"), request.getParameter("publisher"),
				request.getParameter("publicationDate"), request.getParameter("cover_path"),
				request.getParameter("bookInfo"), request.getParameter("bookIndex"), request.getParameter("authorInfo"),
				request.getParameter("category"), request.getParameter("itLevel"), request.getParameter("language"),
				request.getParameter("keyword1"), request.getParameter("keyword2"), request.getParameter("keyword3"), 3
		// Float.parseFloat(request.getParameter("averageStar"))
		);
		System.out.println("register controller에서 "+request.getParameter("ISBN"));
		request.setAttribute("book", book);
		manager.createBook(book);
		
		return "redirect:/book/randomBooks";
	}

}
