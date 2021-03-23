package Blog.Service;

import Blog.Dao.UserLoginMapper;
import Blog.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public List<UserLogin> userLoginFindAll() {
        List<UserLogin> list = userLoginMapper.userLoginFindAll();
        //for (UserLogin userLogin : list) System.out.println(userLogin);
        return list;
    }

    @Override
    public UserLogin userLoginFindOne(Integer userID) {
        UserLogin userLogin = userLoginMapper.userLoginFindOne(userID);
        //System.out.println(userLogin);
        return userLogin;
    }

    @Override
    public void userLoginInsert(UserLogin userLogin) {
        if (userLoginMapper.userLoginFindOne(userLogin.getUserID()) == null) {
            userLoginMapper.userLoginInsert(userLogin);
        } else {
            System.out.println("帐号已存在，无法创建");
        }
    }

    @Override
    public void userLoginUpdate(UserLogin userLogin) {
        if (userLoginMapper.userLoginFindOne(userLogin.getUserID()) != null) {
            userLoginMapper.userLoginUpdate(userLogin);
        } else {
            System.out.println("帐号不存在，无法更新");
        }
    }

    @Override
    public void userLoginDelete(Integer userID) {
        if (userLoginMapper.userLoginFindOne(userID) != null) {
            userLoginMapper.userLoginDelete(userID);
        } else {
            System.out.println("帐号不存在，无法删除");
        }
    }

}
