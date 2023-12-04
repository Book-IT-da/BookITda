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

public class CreateCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String reviewId = request.getParameter("reviewId");
		
		ReviewComment createReview = new ReviewComment(
				0,
				Integer.parseInt(reviewId),
				request.getParameter("rContent"), 
				null
		);

		CommentManager manager = CommentManager.getInstance();
		manager.create(createReview, Integer.parseInt(reviewId));

		
        return "redirect:/review/view";
	}

}
