package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.service.QuestionManager;

public class CreateAnswerController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String value = request.getParameter("qId");
        
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        session.setAttribute("qId", value);
        
        int qId = Integer.parseInt(value);

        Answer ans = new Answer(
                0, request.getParameter("content"),
                qId, userId, null);
        
        QuestionManager quesMan = QuestionManager.getInstance();
        quesMan.createAnswer(qId, ans);
        return "redirect:/question/view";
    }
}
