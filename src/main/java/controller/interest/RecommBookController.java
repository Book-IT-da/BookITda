package controller.interest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Interest;
import model.RecommBook;
import model.service.BookManager;
import model.service.InterestManager;

public class RecommBookController implements Controller {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookManager manager = BookManager.getInstance();
        String userId = (String)request.getSession().getAttribute("userId2");
        
        try {
            List<RecommBook> recommBookList = manager.findRecommList(userId);

            if (recommBookList != null) {
                HttpSession session = request.getSession();
                session.setAttribute("recommBookList", recommBookList);
                return "/interest/recommBook_exist.jsp";
            }
            return "/interest/recommBook_not_exist.jsp";
        }catch (Exception e) {
        	return "/interest/recommBook_not_exist.jsp";
        }
    }
}
