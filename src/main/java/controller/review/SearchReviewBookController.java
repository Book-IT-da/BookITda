package controller.review;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

//검색한 결과의 책 리스트를 가져오기 위한 컨트롤러
public class SearchReviewBookController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BookManager manager = BookManager.getInstance();
		String searchText = request.getParameter("searchText");
		String searchType = "titleType";

		List<Book> bookList = null;
		
		if(searchType!=null) {
			bookList = manager.findBook(searchText, searchType);
		}

		request.setAttribute("bookList", bookList);

		return "/review/searchReviewBook.jsp";
	}

}
