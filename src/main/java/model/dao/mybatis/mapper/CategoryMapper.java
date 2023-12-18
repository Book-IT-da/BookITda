package model.dao.mybatis.mapper;

import java.util.List;
import model.Category;

public interface CategoryMapper {

	// 카테고리 생성
	public int createCategory(String category);

	// 카테고리 수정
	public int modifyCategory(String category, String renew);

	// 카테고리 삭제
	public int deleteCategory(String category);

	// 카테고리 id 찾기
	public int findCategoryId(String category);

	// 카테고리 리스트 반환
	public List<Category> findAllCategory();
}
