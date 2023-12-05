package controller.review;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Review;
import model.ReviewComment;
import model.service.ReviewManager;
import model.service.CommentManager;

public class ReadReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ReviewManager reManager = ReviewManager.getInstance();
		CommentManager coManager = CommentManager.getInstance();
		
		String reviewId = request.getParameter("reviewId");
		
		if (reviewId == null) {
			HttpSession session = request.getSession();
			reviewId = (String)session.getAttribute("reviewId");
		}
		
		Review review = null;
		List<ReviewComment> commentList = null;
		
		review = reManager.read(Integer.parseInt(reviewId));
		commentList = coManager.findCommentList(Integer.parseInt(reviewId));

		request.setAttribute("review", review);
		request.setAttribute("commentList", commentList);

		return "/review/view.jsp";
	}

}
