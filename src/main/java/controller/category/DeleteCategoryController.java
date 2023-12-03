package controller.category;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Category;
import model.service.CategoryManager;

public class DeleteCategoryController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception{
		System.out.println("DeleteCategory controlloer OK");
		CategoryManager manager = CategoryManager.getInstance();
		
		String deleteCate = request.getParameter("deleteCate");
		manager.deleteCategory(deleteCate);
		
		return "redirect:/admin/category/list";
	}
}
