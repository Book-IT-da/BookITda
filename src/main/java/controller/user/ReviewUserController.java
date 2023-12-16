package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Review;
import model.service.ReviewManager;


public class ReviewUserController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";
        }
        
        String userId = request.getParameter("userId");
        
        ReviewManager reManager = ReviewManager.getInstance(); 
        List<Review> review = reManager.findReviewByUser(userId);
        
        request.setAttribute("review", review);
        request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
        
        return "/user/review.jsp";
    }
}
