package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Answer;
import model.service.AnswerManager;

public class DeleteAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String value = request.getParameter("aId");
        int aId = Integer.parseInt(value);
        
        AnswerManager ansMan = AnswerManager.getInstance();
        int result = ansMan.remove(aId);
      
        if (result != 1) {
            return "/question/view.jsp";
        }
        return "redirect:/question/view";
    }
}
