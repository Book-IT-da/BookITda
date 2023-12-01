package controller.review;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.ReviewManager;

public class ListReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("listReview controlloer OK");
		ReviewManager manager = ReviewManager.getInstance();

		return "/review/list.jsp";
	}

}
