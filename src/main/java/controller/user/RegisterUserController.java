package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			return "/user/registerForm.jsp";
		}
		
		User user = new User(
				request.getParameter("userId"),
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("nickname"),
				request.getParameter("email"));
		
		try {
			UserManager userMan = UserManager.getInstance();
			userMan.create(user);
			System.out.println("Create User");
			HttpSession session = request.getSession();
			session.setAttribute("userId", request.getParameter("userId"));
			return "redirect:/get/caegoryList"; // 관심분야 설정으로 변경해야 함
		} catch (ExistingUserException e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
				
	}
}
