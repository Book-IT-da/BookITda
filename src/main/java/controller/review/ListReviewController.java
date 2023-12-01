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
		String reviewText = request.getParameter("reviewTitle_input");
		
		System.out.println("확인 " + reviewText);
		List<Review> reviewList = manager.reviewList();
		
		if(reviewText != null) {
			reviewList = manager.findReviewListByTitle(reviewText);
		}
		
		request.setAttribute("reviewList", reviewList);

		return "/review/list.jsp";
	}

}
