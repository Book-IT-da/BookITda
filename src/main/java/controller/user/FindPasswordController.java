package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;

public class FindPasswordController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            return "/user/findPasswd.jsp";
        }
        
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        UserManager userMan = UserManager.getInstance();
        String passwd = userMan.findPasswd(userId, name, email);
        return "redirect:/user/findPasswdResult?passwd=" + passwd;
    }
}
