package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.AnswerManager;

public class DeleteAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String value = request.getParameter("aId");
        int aId = Integer.parseInt(value);
        
        HttpSession session = request.getSession();
        session.setAttribute("qId", request.getParameter("qId"));

        AnswerManager ansMan = AnswerManager.getInstance();
        int result = ansMan.remove(aId);
      
        return "redirect:/question/view";
    }
}
