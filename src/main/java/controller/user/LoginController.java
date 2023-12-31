package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = request.getParameter("userId");
    	String password = request.getParameter("password");
    	try {
    		UserManager userMan = UserManager.getInstance();
    		userMan.login(userId, password);
    		    
        	HttpSession session = request.getSession();
        	session.setAttribute("userId", userId);
        	
        	User user = userMan.findUser(userId);
        	session.setAttribute("nickname", user.getNickname());
        	
        	return "redirect:/";
    	} catch (Exception e) {
    		request.setAttribute("loginFailed", true);
    		request.setAttribute("exception", e);
            request.setAttribute("msg", "아이디나 비밀번호가 틀립니다.");
    		return "/user/loginForm.jsp";
    	}
    }
}
