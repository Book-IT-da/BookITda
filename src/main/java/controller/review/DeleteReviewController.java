package controller.review;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import model.Review;
import model.service.ReviewManager;

public class DeleteReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		ReviewManager manager = ReviewManager.getInstance();
		String reviewId = request.getParameter("reviewId");

		Review review = null;
		review = manager.read(Integer.parseInt(reviewId));

		request.setAttribute("review", review);

        return "redirect:/review/list";
	}

}
