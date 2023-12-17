package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserManager userMan = UserManager.getInstance();
        List<User> userList = userMan.findUserList();
        
        request.setAttribute("userList", userList);
        request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
        
        return "/admin/user/list.jsp";
    }
}