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

public class CreateReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equals("GET")) {	
    		// GET 
								
			return "/review/createForm.jsp";       
	    }	
    	
		String reviewId = request.getParameter("reviewId");
		Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createDate"));
		
		// POST request
		Review createReview = new Review(
				Integer.parseInt(reviewId),
				Integer.parseInt(request.getParameter("stars")), 
				request.getParameter("level"), 
				request.getParameter("purpose"), 
				request.getParameter("recommObj"), 
				request.getParameter("keyword"), 
				request.getParameter("onelineReview"), 
				request.getParameter("multilineReview"), 
				request.getParameter("title"), 
				request.getParameter("nickname"), 
				request.getParameter("author"),
				request.getParameter("publisher"),
				createDate
		);

		ReviewManager manager = ReviewManager.getInstance();
		manager.create(createReview);

		
        return "redirect:/review/list";
	}

}
