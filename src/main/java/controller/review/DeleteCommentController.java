package controller.review;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import model.ReviewComment;
import model.service.CommentManager;

public class DeleteCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CommentManager manager = CommentManager.getInstance();
		String commentId = request.getParameter("commentId");

		HttpSession session = request.getSession();
		session.setAttribute("reviewId", request.getParameter("reviewId"));
		
		manager.removeComment(Integer.parseInt(commentId));
		
        return "redirect:/review/view";
	}

}
