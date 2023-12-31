package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.Category;
import model.service.BookManager;
import model.service.CategoryManager;

//책을 수정하기 위한 컨트롤러
public class ModifyBookInfoController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookManager manager = BookManager.getInstance();
	
		if(request.getMethod().equals("GET")) {
			String ISBN = request.getParameter("ISBN");

			Book book = null;
			book = manager.findBookInfo(ISBN);

			request.setAttribute("book", book);
			
			CategoryManager cateM = CategoryManager.getInstance();

			List<Category> categories = null;

			categories = cateM.findAllCategory();
			request.setAttribute("categories", categories);

			return "/admin/book/updateForm.jsp";
		}
		else {
			Book book = new Book(
					request.getParameter("ISBN"),
					request.getParameter("title"),
					request.getParameter("author"),
					request.getParameter("publisher"),
					request.getParameter("publicationDate"),
					request.getParameter("cover_path"),
					request.getParameter("bookInfo"),
					request.getParameter("bookIndex"),
					request.getParameter("authorInfo"),
					request.getParameter("category"),
					request.getParameter("itLevel"),
					request.getParameter("language"),
					request.getParameter("keyword1"),
					request.getParameter("keyword2"),
					request.getParameter("keyword3"),
					3
			);
			request.setAttribute("book", book);
			manager.modifyBook(book);
			
			return "redirect:/book/allList";
		}
	}
}
