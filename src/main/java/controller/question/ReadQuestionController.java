package controller.question;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.Question;
import model.service.AnswerManager;
import model.service.QuestionManager;

public class ReadQuestionController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionManager quesMan = QuestionManager.getInstance();
		AnswerManager ansMan = AnswerManager.getInstance();
		
		String value = request.getParameter("qId");
	
		Question ques = null;
		List<Answer> answerList = null;
		
		try {
			int qId = Integer.parseInt(value);
			ques = quesMan.read(qId);
			answerList = ansMan.findAnswerList(qId);
		    request.setAttribute("answerList", answerList);
			
		} catch (Exception e) {
			return "redirect:/question/list";
		}
		request.setAttribute("ques", ques);
		return "/question/view.jsp";
	}
}
	
