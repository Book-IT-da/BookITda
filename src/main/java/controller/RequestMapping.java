package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.book.*;
import controller.review.*;
import controller.interest.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
	
       // 사용자 관련
       mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
       mappings.put("/user/login", new LoginController());
       mappings.put("/user/logout", new LogoutController());
       mappings.put("/user/register", new RegisterUserController());

      // 마이페이지 관련
       mappings.put("/user/mypage", new ForwardController("/user/mypage.jsp"));
       mappings.put("/user/update", new UpdateUserController());

        // 리뷰 관련
        mappings.put("/review/list", new ListReviewController());
        mappings.put("/review/view", new ReadReviewController());
        mappings.put("/review/update", new UpdateReviewController());
        
        //// 도서 검색 관련
        mappings.put("/book/searchList", new ListBookController()); // 검색한 책 리스트
        mappings.put("/book/randomBooks", new ViewRandomBookController()); //랜덤 책 리스트
        mappings.put("/book/bookInfo", new ViewBookInfoController()); //책 세부 정보 보기
        mappings.put("/admin/book/modify", new ViewModifyBookInfoController()); //책 정보 수정
        mappings.put("/admin/book/delete", new DeleteBookController()); //책 삭제
        mappings.put("/admin/book/register", new RegisterBookController()); //책 추가
        mappings.put("/admin/book/register/form",new ForwardController("/admin/book/register/registerForm.jsp")); //책 추가 폼으로 가기 

        // 도서 검색 관련
        mappings.put("/book/searchList", new ListBookController());
        // 관심사 생성
        mappings.put("/get/caegoryList", new GetCategoryListController());
        mappings.put("/interest/interest_createForm", new ForwardController("/interest/interest_createForm.jsp"));
        mappings.put("/user/interest/create", new GetInterestController());
        // 관심사 수정 폼
        mappings.put("/user/get/interest", new GetInterestController());
        mappings.put("/user/update/interest", new ForwardController("/interest/interest_updateForm.jsp"));
        // 관심 카테고리, 언어, 수준 수정
        mappings.put("/user/update/interest/category", new UpdateInterestCategoryController());
        mappings.put("/user/update/interest/language", new UpdateInterestLanguageController());
        mappings.put("/user/update/interest/level", new UpdateUserLevelController());	
        // 도서 추천
        mappings.put("/recommBook/view", new RecommBookController());
        mappings.put("/recommBook_exist", new ForwardController("/interest/recommBook_exist.jsp"));
        mappings.put("/recommBook_not_exist", new ForwardController("/interest/recommBook_not_exist.jsp"));
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
    	System.out.print(uri);
        return mappings.get(uri);
    }
}
