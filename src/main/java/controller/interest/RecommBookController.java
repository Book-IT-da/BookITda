package controller.interest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.RecommBook;
import model.service.BookManager;

public class RecommBookController implements Controller {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookManager manager = BookManager.getInstance();
        String userId = (String) request.getSession().getAttribute("userId");

        try {
            List<RecommBook> recommBookList = manager.findRecommList(userId);
            if (recommBookList != null && !recommBookList.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("recommBookList", recommBookList);
                return "/interest/recommBook_exist.jsp";
            } else {
                return "/interest/recommBook_not_exist.jsp";
            }
        } catch (Exception e) {
            return "/interest/recommBook_not_exist.jsp";
        }
    }
}
