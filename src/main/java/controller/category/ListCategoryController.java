package controller.category;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Category;
import model.service.CategoryManager;

//모든 카테고리 리스트 
public class ListCategoryController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListCategoryList controlloer OK");
		CategoryManager manager = CategoryManager.getInstance();

		List<Category> categories = null;

		categories = manager.findAllCategory();
		request.setAttribute("categories", categories);

		return "/admin/category/list.jsp";
	}
}
