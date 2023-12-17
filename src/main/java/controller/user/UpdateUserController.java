package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class UpdateUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			String updateId = request.getParameter("userId");
			
			UserManager userMan = UserManager.getInstance();
			User user = userMan.findUser(updateId);
			request.setAttribute("user", user);
			
			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateId, session)) {
				return "/user/updateForm.jsp"; 
			}
			
			request.setAttribute("updateFailed", true);
			return "/user/mypage.jsp";
		}
				
		User updateUser = new User(
				request.getParameter("userId"),
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("nickname"),
				request.getParameter("email"));
		
		UserManager userMan = UserManager.getInstance();
		userMan.update(updateUser);
		
		// 닉네임 수정 갱신
        HttpSession session = request.getSession();
		session.setAttribute("nickname", request.getParameter("nickname"));
		
		return "redirect:/user/mypage";
	}
}
