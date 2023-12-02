package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = request.getParameter("userId");
    	String password = request.getParameter("password");
    	try {
    		UserManager.getInstance().login(userId, password);
    		

        	HttpSession session = request.getSession();
        	session.setAttribute("userId", userId);
        	
        	return "redirect:/book/searchList";
    	} catch (Exception e) {
    		request.setAttribute("loginFailed", true);
    		request.setAttribute("exception", e);
    		return "/user/loginForm.jsp";
    	}
    }
}