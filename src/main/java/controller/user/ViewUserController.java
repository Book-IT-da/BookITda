package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager userMan = UserManager.getInstance();
        String userId = request.getParameter("userId");
        
        User user = null;
        try {
            user = userMan.findUser(userId);
        } catch (UserNotFoundException e) {
            return "redirect:/admin/user/list";
        }
        
        request.setAttribute("user", user);
        return "/admin/user/view.jsp";
    }
}
