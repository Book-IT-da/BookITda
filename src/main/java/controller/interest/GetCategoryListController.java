package controller.interest;

import model.Category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.CategoryManager;

public class GetCategoryListController implements Controller {
    private CategoryManager categoryManager = CategoryManager.getInstance();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            try {
                List<Category> categoryList = categoryManager.findAllCategory();
                HttpSession session = request.getSession();
                session.setAttribute("categoryList", categoryList);
                return "redirect:/user/interest/create";
            } catch (Exception e) {
                return "redirect:/user/interest/create";
            }
        } else {
            try {
                List<Category> categoryList = categoryManager.findAllCategory();
                HttpSession session = request.getSession();
                session.setAttribute("categoryList", categoryList);
                return "redirect:/user/get/interest";
            } catch (Exception e) {
                return "redirect:/user/get/interest";
            }
        }
    }
}
