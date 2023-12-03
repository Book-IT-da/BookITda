package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;

public class ReadQuestionController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionManager quesMan = QuestionManager.getInstance();
		String value = request.getParameter("qId");
	
		Question ques = null;
		try {
			int qId = Integer.parseInt(value);
			ques = quesMan.read(qId);
		} catch (Exception e) {
			return "redirect:/question/list";
		}
		request.setAttribute("ques", ques);
		return "/question/view.jsp";
	}
}
	
