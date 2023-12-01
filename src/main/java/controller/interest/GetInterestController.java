package controller.interest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Interest;
import model.service.InterestManager;

public class GetInterestController implements Controller {
    private InterestManager interestManager = InterestManager.getInstance();
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String)request.getSession().getAttribute("userId2");
        
        try {
            //임시로 lulu값 넣어놓음
            Interest userLastInterest = interestManager.findInterestById("lulu");
            System.out.println("userLastInterest: " + userLastInterest.toString());
            if (userLastInterest != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userLastInterest", userLastInterest);
                return "redirect:/user/update/interest";
            }
            return "/interest/interest_updateForm.jsp";
        }catch (Exception e) {
            return "/interest/interest_updateForm.jsp";
        }
    }
}
