package controller.question;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;

public class ListQuestionController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionManager quesMan = QuestionManager.getInstance();
		
        String type = request.getParameter("type");
		String searchKeyword = request.getParameter("searchKeyword");
		
		List<Question> questionList = quesMan.findQuestionList();
		
		if (searchKeyword != null) {
			if (type.equals("title")) 
				questionList = quesMan.findQuestionByTitle(searchKeyword);
			else if (type.equals("writer"))
				questionList = quesMan.findQuestionByName(searchKeyword);
		}
		
		request.setAttribute("questionList", questionList);
		
		return "/question/list.jsp";
	}
}
