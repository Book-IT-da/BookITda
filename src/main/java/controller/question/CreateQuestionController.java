package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;

public class CreateQuestionController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			return "/question/createForm.jsp";
		}
		
		// 문제: 아이디당 1개의 질문 글만 입력됨.
		Question question = new Question(
				0, request.getParameter("title"),
				request.getParameter("content"),
				0, request.getParameter("userId"), null);
		
		try {
			QuestionManager quesMan = QuestionManager.getInstance();
			quesMan.create(question);
			System.out.println("Create Question");
			return "redirect:/question/list"; // 추후에 view로 변경하기
		} catch (Exception e) {
			request.setAttribute("exception", e);
			return "/question/list.jsp";
		}
	}
}
