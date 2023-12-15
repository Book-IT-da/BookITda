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
    	
		String userId = request.getParameter("userId");
		String ISBN = request.getParameter("ISBN");
		
		// POST request
		Review createReview = new Review(
				0, 
				Integer.parseInt(request.getParameter("stars")), 
				request.getParameter("level"), 
				request.getParameter("purpose"), 
				request.getParameter("recommObj"), 
				request.getParameter("keyword"), 
				request.getParameter("onelineReview"), 
				request.getParameter("multilineReview"), 
				userId, 
				ISBN, 
				null
		);

		ReviewManager manager = ReviewManager.getInstance();
		manager.createReview(createReview);

		
        return "redirect:/review/list";
	}

}
