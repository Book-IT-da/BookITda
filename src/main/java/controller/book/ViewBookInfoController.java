package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.Review;
import model.service.BookManager;
import model.service.ReviewManager;

//선택한 책의 상세 정보를 보기 위한 컨트롤러
public class ViewBookInfoController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookManager manager = BookManager.getInstance();
		String ISBN = request.getParameter("ISBN");

		Book book = null;
		book = manager.findBookInfo(ISBN);

		request.setAttribute("book", book);
		
		// 도서 정보 하단 리뷰 목록
		ReviewManager rManager = ReviewManager.getInstance();
		List<Review> reviewList = rManager.findReviewList(ISBN);
		request.setAttribute("reviewList", reviewList);
		
		return "/book/bookInfo.jsp";
	}	
}
