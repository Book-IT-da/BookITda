package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class DeleteUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			String deleteId = request.getParameter("userId");
			
			UserManager userMan = UserManager.getInstance();
			User user = userMan.findUser(deleteId);
			request.setAttribute("user", user);
			
			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(deleteId, session)) {
				return "/user/delete.jsp";
			}
			request.setAttribute("deleteFailed", true);
			return "/user/mypage.jsp";
		}
				
		HttpSession session = request.getSession();
		String deleteId = (String) session.getAttribute("userId");
		UserManager userMan = UserManager.getInstance();
		
		if (UserSessionUtils.isLoginUser(deleteId, session)) {
			userMan.delete(deleteId);
			return "redirect:/user/logout";
		}
		
		User user = userMan.findUser(deleteId);
		request.setAttribute("user", user);
		request.setAttribute("deleteFailed", true);
		return "/user/mypage.jsp";
	}
}
