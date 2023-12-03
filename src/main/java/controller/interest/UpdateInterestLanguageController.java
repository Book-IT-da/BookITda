package controller.interest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Interest;
import model.service.InterestManager;

public class UpdateInterestLanguageController implements Controller {
    private InterestManager interestManager = InterestManager.getInstance();
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String)request.getSession().getAttribute("userId");
        // 체크박스 그룹에 대한 값을 List로 받아옴
        String[] interestLanguages = request.getParameterValues("interestLanguages");

        // List<Integer> 형태로 변환
        List<Integer> categoryList = Arrays.asList(0);
        List<Integer> languageList = Arrays.stream(interestLanguages).map(Integer::parseInt).collect(Collectors.toList());

        // 임시 값
        int levelId = 0;

        // DTO 생성
        Interest userInterest = new Interest(categoryList, languageList, levelId);

        try {
            interestManager.modifyInterestLang(userId ,userInterest);
            return "redirect:/recommBook/view";
        }catch (Exception e) {
            return "/interest/interest_updateForm.jsp";
        }
    }
}
