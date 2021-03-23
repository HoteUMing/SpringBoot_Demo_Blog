package Blog.Service;

import Blog.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    //查询所有用户信息
    List<UserInfo> userInfoFindAll();

    //查询指定用户信息
    UserInfo userInfoFindOne(Integer userID);

    //查询指定用户信息
    UserInfo userInfoFindName(String userName);

    //新增用户信息
    void userInfoInsert(UserInfo userInfo);

    //更新指定用户的信息
    void userInfoUpdate(UserInfo userInfo);

    //删除指定用户信息
    void userInfoDelete(Integer userID);

}
