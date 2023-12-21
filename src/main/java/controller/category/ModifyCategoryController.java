package controller.category;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Category;
import model.service.CategoryManager;

//카테고리 수정
public class ModifyCategoryController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception{
		CategoryManager manager = CategoryManager.getInstance();
		
		String category = request.getParameter("originalCate");
		String renew =  request.getParameter("renew");
		
		manager.modifyCategory(category, renew);
		
		return "redirect:/admin/category/list";
	}

}
