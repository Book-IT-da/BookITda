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
		String userId = request.getParameter("userId");
		Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createDate"));
		
		// POST request
		ReviewComment updateComment = new ReviewComment(
				0,
				Integer.parseInt(commentId),
				request.getParameter("rContent"),
				userId,
				null
		);

		ReviewManager manager = ReviewManager.getInstance();
		manager.updateComment(reviewComment, Integer.parseInt(commentId));

		
        return "redirect:/review/view";
	}

}
