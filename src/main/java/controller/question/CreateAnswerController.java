package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.service.AnswerManager;

public class CreateAnswerController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String value = request.getParameter("qId");
        int qId = Integer.parseInt(value);
        
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        
        Answer ans = new Answer(
                0, request.getParameter("content"),
                qId, userId, null);
        
        try {
            AnswerManager ansMan = AnswerManager.getInstance();
            ansMan.create(qId, ans);
            return "redirect:/question/view";
        } catch (Exception e) {
            return "question/view.jsp";
        }
    }
}
