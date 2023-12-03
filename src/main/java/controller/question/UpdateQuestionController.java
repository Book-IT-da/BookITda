package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;

public class UpdateQuestionController implements Controller {
	 @Override
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 if (request.getMethod().equals("GET")) {
			 String value = request.getParameter("qId");
			 
			 QuestionManager quesMan = QuestionManager.getInstance();
			 int updateQId = Integer.parseInt(value);
			 Question ques = quesMan.read(updateQId);
			 request.setAttribute("ques", ques);
			 
			 HttpSession session = request.getSession();
			 String userId = (String) session.getAttribute("userId");
			 
			 if (userId.equals(ques.getUserId())) {
				 return "/question/updateForm.jsp";
			 }
			 return "/question/view.jsp";
		 }
		
		 String value = request.getParameter("qId");
		 int updateQId = Integer.parseInt(value);
		 
		 Question updateQues = new Question(
				 updateQId, request.getParameter("title"),
				 request.getParameter("content"),
				 0, null, request.getParameter("nickname"), null);
		 
		 QuestionManager quesMan = QuestionManager.getInstance();
		 quesMan.update(updateQues);
		 return "redirect:/question/view";
				 
	 }
	 
}
