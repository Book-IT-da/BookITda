package controller.review;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import model.Review;
import model.ReviewComment;
import model.service.ReviewManager;

public class UpdateCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReviewComment reviewComment = null;
		String commentId = request.getParameter("commentId");
		String reviewId = request.getParameter("reviewId");
		String userId = request.getParameter("userId");
		
		HttpSession session = request.getSession();
		session.setAttribute("reviewId", reviewId);
		
		ReviewComment updateComment = new ReviewComment(
				Integer.parseInt(commentId),
				Integer.parseInt(reviewId),
				userId,
				request.getParameter("rContent"),
				null
		);

		ReviewManager manager = ReviewManager.getInstance();
		manager.updateComment(updateComment);

		
        return "redirect:/review/view";
	}

}
