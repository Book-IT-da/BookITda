package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;

public class CreateQuestionController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			return "/question/createForm.jsp";
		}
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		Question question = new Question(
				0, request.getParameter("title"),
				request.getParameter("content"),
				0, userId, null);
		
		try {
			QuestionManager quesMan = QuestionManager.getInstance();
			quesMan.createQuestion(question);
			System.out.println("Create Question");
			return "redirect:/question/list"; 
		} catch (Exception e) {
			request.setAttribute("exception", e);
			return "/question/list.jsp";
		}
	}
}
