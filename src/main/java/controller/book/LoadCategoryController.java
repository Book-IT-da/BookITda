package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Category;
import model.service.CategoryManager;

public class LoadCategoryController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListCategoryList controlloer OK");
		CategoryManager manager = CategoryManager.getInstance();

		List<Category> categories = null;

		categories = manager.findAllCategory();
		request.setAttribute("categories", categories);

		return "/admin/book/registerForm.jsp";
	}
}
