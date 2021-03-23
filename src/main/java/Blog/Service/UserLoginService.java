package Blog.Service;

import Blog.entity.UserLogin;

import java.util.List;

public interface UserLoginService {

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
