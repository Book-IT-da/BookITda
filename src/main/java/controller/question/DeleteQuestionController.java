package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;

public class DeleteQuestionController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String value = request.getParameter("qId");
		int qId = Integer.parseInt(value);
		
		QuestionManager quesMan = QuestionManager.getInstance();
		Question ques = quesMan.read(qId);
		request.setAttribute("ques", ques);
		 
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		if (userId.equals(ques.getUserId()) || (userId.equals("admin"))) {
			quesMan.removeQuestion(qId);
			return "redirect:/question/list";
		}
		
		/* 삭제 불가능 */
	return "/question/view.jsp";
	}	
}
