package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;

public class QuestionUserController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form";
		}
		
		String userId = request.getParameter("userId");

		QuestionManager quesMan = QuestionManager.getInstance();
		List<Question> questionList = quesMan.findQuestionByUser(userId);
		
		request.setAttribute("questionList", questionList);
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
		
		return "/user/question.jsp";
	}
}
