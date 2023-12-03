package controller.interest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Interest;
import model.service.InterestManager;

public class CreateInterestController implements Controller {
    private InterestManager interestManager = InterestManager.getInstance();
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            return "/interest/interest_createForm.jsp";
        }
        String userId = (String)request.getSession().getAttribute("userId");
        // 체크박스 그룹에 대한 값을 List로 받아옴
        String[] interestCategories = request.getParameterValues("interestCategory");
        String[] interestLanguages = request.getParameterValues("interestLanguages");
        String[] userLevels = request.getParameterValues("userLevel");

        // List<Integer> 형태로 변환
        List<Integer> categoryList = Arrays.stream(interestCategories).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> languageList = Arrays.stream(interestLanguages).map(Integer::parseInt).collect(Collectors.toList());

        // 직접 값 가져오기
        int levelId = Integer.parseInt(userLevels[0]);

        // DTO 생성
        Interest userInterest = new Interest(categoryList, languageList, levelId);

        try {
            interestManager.createUserInterest(userId ,userInterest);
            return "redirect:/recommBook/view";
        }catch (Exception e) {
            return "/interest/interest_createForm.jsp";
        }
    }
}
