package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

// 랜덤 책 리스트를 가져오기 위한 컨트롤러
public class ViewRandomBookController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BookManager manager = BookManager.getInstance();

		List<Book> books = null;
		books = manager.findRandomBook();

		request.setAttribute("books", books);

		return "/book/randomBooks.jsp";
	}
}
