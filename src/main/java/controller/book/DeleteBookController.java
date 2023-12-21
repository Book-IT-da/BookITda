package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

//책 삭제를 위한 컨트롤러
public class DeleteBookController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("deleteBook Controller OK");
		BookManager manager = BookManager.getInstance();
		String ISBN =request.getParameter("ISBN");

		manager.deleteBook(ISBN);

		return "redirect:/book/allList";

	}
}
