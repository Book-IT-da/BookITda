package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

//검색한 결과의 책 리스트를 가져오기 위한 컨트롤러
public class ListBookController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookManager manager = BookManager.getInstance();
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");

		List<Book> books = null;
		if(searchType!=null) {
			books = manager.findBook(searchText, searchType);
		}

		request.setAttribute("books", books);

		return "/book/searchList.jsp";
	}

}
