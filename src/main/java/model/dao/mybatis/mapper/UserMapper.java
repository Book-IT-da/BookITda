package model.dao.mybatis.mapper;

import model.User;

public interface UserMapper {
    // 회원 정보 수정
    public int modifyUser(User user);
}
