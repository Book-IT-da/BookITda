package controller.review;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Review;
import model.service.ReviewManager;

public class ListReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ReviewManager manager = ReviewManager.getInstance();
		List<Review> reviewList = manager.reviewList();
		
		request.setAttribute("reviewList", reviewList);

		return "/review/list.jsp";
	}

}
