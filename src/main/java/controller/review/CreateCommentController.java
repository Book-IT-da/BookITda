package controller.review;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import model.ReviewComment;
import model.service.ReviewManager;

public class CreateCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String reviewId = request.getParameter("reviewId");
		String userId = request.getParameter("userId");
		
		HttpSession session = request.getSession();
		session.setAttribute("reviewId", reviewId);
		
		ReviewComment createComment = new ReviewComment(
				0,
				Integer.parseInt(reviewId),
				userId,
				request.getParameter("rContent"), 
				null
		);

		ReviewManager manager = ReviewManager.getInstance();
		manager.createComment(createComment);

		
        return "redirect:/review/view";
	}

}
