package controller.interest;

import model.Category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.InterestManager;

public class GetCategoryListController implements Controller {
    private InterestManager interestManager = InterestManager.getInstance();
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        try {
            List<Category> categoryList = interestManager.findCategoryList();
            HttpSession session = request.getSession();
            session.setAttribute("categoryList", categoryList);
            return "redirect:/user/interest/create";
        }catch (Exception e) {
            return "redirect:/user/interest/create";
        }
    }
}
