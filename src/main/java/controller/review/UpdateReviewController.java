package controller.review;

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
    		Review review = manager.read(Integer.parseInt(reviewId));
    		request.setAttribute("review", review);
								
			return "/review/updateForm.jsp";       
	    }	
    	
    	// POST request
		Review updateReview = new Review(
				
				);

		ReviewManager manager = ReviewManager.getInstance();
		manager.update(updateReview);			
        return "redirect:/review/list";
	}

}
