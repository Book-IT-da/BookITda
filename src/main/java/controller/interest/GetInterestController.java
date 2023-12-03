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
        String userId = (String)request.getSession().getAttribute("userId");
        System.out.println("userId:" + userId);
        try {
            Interest userLastInterest = interestManager.findInterestById(userId);
            if (userLastInterest != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userLastInterest", userLastInterest);
                System.out.println("userLastInterest:" + userLastInterest);
                return "redirect:/user/update/interest";
            } else {
                return "/interest/interest_updateForm.jsp";
            }
        }catch (Exception e) {
            return "/interest/interest_updateForm.jsp";
        }
    }
}
