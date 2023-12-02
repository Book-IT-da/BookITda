package controller.review;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Review;
import model.service.ReviewManager;

public class UpdateReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ReviewManager manager = ReviewManager.getInstance();
		String reviewId = request.getParameter("reviewId");

		Review review = null;
		review = manager.read(Integer.parseInt(reviewId));

		request.setAttribute("review", review);

		return "/review/view.jsp";
	}

}
