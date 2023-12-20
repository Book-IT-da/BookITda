package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;

public class FindUserIdController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
               return "/user/findUserId.jsp";
        }
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
                
        UserManager userMan = UserManager.getInstance();
        String userId = userMan.findUserId(name, email);
        return "redirect:/user/findUserIdResult?userId=" + userId;
    }
}
