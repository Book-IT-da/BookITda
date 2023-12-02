package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

//선택한 책의 상세 정보를 보기 위한 컨트롤러
public class ViewBookInfoController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ViewBookInfoController OK");	
		BookManager manager = BookManager.getInstance();
		String ISBN = request.getParameter("ISBN");

		Book book = null;
		book = manager.findBookInfo(ISBN);

		request.setAttribute("book", book);
		System.out.print("info에 왔음");

		return "/book/bookInfo.jsp";
	}	
}
