package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Answer;
import model.service.QuestionManager;

public class AnswerUserController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";
        }
        
        String userId = request.getParameter("userId");
        
        QuestionManager quesMan = QuestionManager.getInstance();
        List<Answer> answerList = quesMan.findAnswerByUser(userId);
        
        request.setAttribute("answerList", answerList);
        request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
        
        return "/user/answer.jsp";
    }
}