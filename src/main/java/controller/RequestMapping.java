package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.book.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));

        // 리뷰 관련
        
        //// 도서 검색 관련
        mappings.put("/book/searchList", new ListBookController()); // 검색한 책 리스트
        mappings.put("/book/randomBooks", new ViewRandomBookController()); //랜덤 책 리스트
        mappings.put("/book/bookInfo", new ViewBookInfoController()); //책 세부 정보 보기
        mappings.put("/admin/book/modify", new ViewModifyBookInfoController()); //책 정보 수정
        mappings.put("/admin/book/delete", new DeleteBookController()); //책 삭제
        mappings.put("/admin/book/register", new RegisterBookController()); //책 추가
        mappings.put("/admin/book/register/form",new ForwardController("/admin/book/register/registerForm.jsp")); //책 추가 폼으로 가기 

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
    	System.out.print(uri);
        return mappings.get(uri);
    }
}