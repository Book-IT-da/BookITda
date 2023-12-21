package controller.category;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Category;
import model.service.CategoryManager;

//카테고리 생성
public class RegisterCategoryController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception{
		CategoryManager manager = CategoryManager.getInstance();
		
		String newCate = request.getParameter("newCate");
		manager.createCategory(newCate);
		
		return "redirect:/admin/category/list";
	}
}
