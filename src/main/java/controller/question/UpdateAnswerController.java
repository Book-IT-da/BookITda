package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.service.QuestionManager;

public class UpdateAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String aId = request.getParameter("aId");
        String qId = request.getParameter("qId");
        String userId = request.getParameter("userId");
        
        HttpSession session = request.getSession();
        session.setAttribute("qId", qId);
        
        Answer updateAnswer = new Answer(
                Integer.parseInt(aId),
                request.getParameter("answer"),
                Integer.parseInt(qId),
                userId, 
                null);
                
        QuestionManager quesMan = QuestionManager.getInstance();
        quesMan.updateAnswer(updateAnswer);
        
        return "redirect:/question/view";
        
    }
}

