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

public class UpdateReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equals("GET")) {	
    		// GET 
			String reviewId = request.getParameter("reviewId");
    		
    		ReviewManager manager = ReviewManager.getInstance();
    		Review review = manager.readReview(Integer.parseInt(reviewId));
    		request.setAttribute("review", review);
								
			return "/review/updateForm.jsp";       
	    }	
    	
		String reviewId = request.getParameter("reviewId");
		Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createDate"));
		
		// POST request
		Review updateReview = new Review(
				Integer.parseInt(reviewId),
				Integer.parseInt(request.getParameter("stars")), 
				request.getParameter("level"), 
				request.getParameter("purpose"), 
				request.getParameter("recommObj"), 
				request.getParameter("keyword"), 
				request.getParameter("onelineReview"), 
				request.getParameter("multilineReview"), 
				request.getParameter("title"), 
				request.getParameter("cover_path"),
				request.getParameter("userId"), 
				request.getParameter("nickname"), 
				request.getParameter("author"),
				request.getParameter("publisher"),
				request.getParameter("ISBN"),
				createDate
		);

		ReviewManager manager = ReviewManager.getInstance();
		manager.updateReview(updateReview);

		
        return "redirect:/review/list";
	}

}
