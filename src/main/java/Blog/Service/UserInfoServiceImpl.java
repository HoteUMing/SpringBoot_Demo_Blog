package Blog.Service;

import Blog.Dao.UserInfoMapper;
import Blog.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> userInfoFindAll() {
        List<UserInfo> list = userInfoMapper.userInfoFindAll();
        //for (UserInfo userInfo : list) System.out.println(userInfo);
        return list;
    }

    @Override
    public UserInfo userInfoFindOne(Integer userID) {
        UserInfo userInfo = userInfoMapper.userInfoFindOne(userID);
        //System.out.println(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo userInfoFindName(String userName) {
        UserInfo userInfo = userInfoMapper.userInfoFindName(userName);
        //System.out.println(userInfo);
        return userInfo;
    }

    @Override
    public void userInfoInsert(UserInfo userInfo) {
        if (userInfoMapper.userInfoFindOne(userInfo.getUserID()) == null) {
            userInfoMapper.userInfoInsert(userInfo);
        } else {
            System.out.println("用户已存在，无法创建");
        }
    }

    @Override
    public void userInfoUpdate(UserInfo userInfo) {
        if (userInfoMapper.userInfoFindOne(userInfo.getUserID()) != null) {
            userInfoMapper.userInfoUpdate(userInfo);
        } else {
            System.out.println("用户不存在，无法修改");
        }
    }

    @Override
    public void userInfoDelete(Integer userID) {
        if (userInfoMapper.userInfoFindOne(userID) != null) {
            userInfoMapper.userInfoDelete(userID);
        } else {
            System.out.println("用户不存在，无法删除");
        }
    }

}
