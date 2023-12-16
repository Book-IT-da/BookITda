package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ReviewComment;
import model.service.ReviewManager;

public class CommentUserController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";
        }
        
        String userId = request.getParameter("userId");
        
        ReviewManager reManager = ReviewManager.getInstance(); 
        List<ReviewComment> reviewComment = reManager.findReviewCommentByUser(userId);
        
        request.setAttribute("reviewComment", reviewComment);
        request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
        
        return "/user/reviewComment.jsp";
    }
}
