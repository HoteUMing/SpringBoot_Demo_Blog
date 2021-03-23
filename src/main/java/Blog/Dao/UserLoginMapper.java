package Blog.Dao;

import Blog.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLoginMapper {

    //查询所有帐号密码
    List<UserLogin> userLoginFindAll();

    //查询指定帐号密码
    UserLogin userLoginFindOne(Integer userID);

    //新增帐号密码
    void userLoginInsert(UserLogin userLogin);

    //更新指定帐号的密码
    void userLoginUpdate(UserLogin userLogin);

    //删除指定帐号的密码
    void userLoginDelete(Integer userID);

}
