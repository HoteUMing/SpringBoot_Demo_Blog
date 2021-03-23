package Blog.Controller;

import Blog.Service.UserInfoServiceImpl;
import Blog.Service.UserLoginServiceImpl;
import Blog.entity.UserInfo;
import Blog.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserLoginServiceImpl userLoginService;
    @Autowired
    private UserInfoServiceImpl userInfoService;

    @GetMapping("/userLogin")
    public String turnlogin() {
        return "/Admin/login";
    }

    @PostMapping("/userLogin")
    public String login(@RequestParam(name = "userName") String userName,
                        @RequestParam(name = "userPassword") String userPassword,
                        HttpServletRequest request) {
        if (userName != null && !("".equals(userName))) {
            UserInfo userInfo = userInfoService.userInfoFindName(userName);
            if (userInfo != null) {
                UserLogin userLogin = userLoginService.userLoginFindOne(userInfo.getUserID());
                if (userPassword != null && !("".equals(userPassword))) {
                    if (userPassword.equals(userLogin.getUserPassword())) {
                        request.getSession().setAttribute("testBlogUserInfo", userInfo);
                        return "redirect:/";
                    } else {
                        request.setAttribute("errorMessage", "密码不正确");
                    }
                } else {
                    request.setAttribute("errorMessage", "密码不能为空");
                }
            } else {
                request.setAttribute("errorMessage", "用户名错误，该帐号不存在");
            }
        } else {
            request.setAttribute("errorMessage", "用户名不能为空");
        }
        return "/Admin/login";
    }

    @GetMapping("/userRegister")
    public String turnRegister() {
        return "/Admin/register";
    }

    @PostMapping("/userRegister")
    public String Register(@RequestParam(name = "userName") String userName,
                           @RequestParam(name = "userPassword1") String userPassword1,
                           @RequestParam(name = "userPassword2") String userPassword2,
                           HttpServletRequest request) {
        if (userName != null && !("".equals(userName))) {
            UserInfo userInfo = userInfoService.userInfoFindName(userName);
            if (userInfo == null) {
                if (userPassword1 != null && !("".equals(userPassword1)) && userPassword2 != null && !("".equals(userPassword2))) {
                    if (userPassword1.equals(userPassword2)) {
                        request.setAttribute("errorMessage", "帐号创建成功，请登录");
                        userInfo = new UserInfo();
                        userInfo.setUserName(userName);
                        userInfo.setCreateTime(System.currentTimeMillis());
                        userInfo.setModifyTime(userInfo.getCreateTime());
                        userInfoService.userInfoInsert(userInfo);
                        UserLogin userLogin = new UserLogin();
                        userLogin.setUserID(userInfoService.userInfoFindName(userName).getUserID());
                        userLogin.setUserPassword(userPassword1);
                        userLoginService.userLoginInsert(userLogin);
                        return "redirect:/userLogin";
                    } else {
                        request.setAttribute("errorMessage", "两次输入的密码不一致，请重新输入");
                    }
                } else {
                    request.setAttribute("errorMessage", "密码不能为空");
                }
            } else {
                request.setAttribute("errorMessage", "用户名已存在，请更换用户名");
            }
        } else {
            request.setAttribute("errorMessage", "用户名不能为空");
        }
        return "/Admin/register";
    }

    @GetMapping("/userLogout")
    public String Logout(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("testBlogUserInfo");
        if (userInfo != null) {
            request.getSession().removeAttribute("testBlogUserInfo");
        }
        return "redirect:/";
    }

    @GetMapping("/Admin/userInfo")
    public String turnUserInfo(HttpServletRequest request) {
        return IsConnected(request) == null ? "/Admin/updateUserInfo" : IsConnected(request);
    }

    @PostMapping("/Admin/userInfo/{userID}")
    public String userInfo(@PathVariable(name = "userID") Integer userID,
                           @RequestParam(name = "userName") String userName,
                           @RequestParam(name = "userGender") String userGender,
                           @RequestParam(name = "userDesc") String userDesc,
                           HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("testBlogUserInfo");
        if (IsConnected(request) != null || !userID.equals(userInfo.getUserID())) {
            return IsConnected(request);
        }
        userInfo = new UserInfo();
        userInfo.setUserID(userID);
        userInfo.setUserName(userName);
        userInfo.setUserGender(userGender);
        userInfo.setUserDesc(userDesc);
        userInfo.setModifyTime(System.currentTimeMillis());
        userInfoService.userInfoUpdate(userInfo);
        request.getSession().setAttribute("testBlogUserInfo", userInfoService.userInfoFindOne(userID));
        return "redirect:/Admin/main";
    }

    @GetMapping("/Admin/userLogin")
    public String turnUserLogin(HttpServletRequest request) {
        return IsConnected(request) == null ? "/Admin/updateUserLogin" : IsConnected(request);
    }

    @PostMapping("/Admin/userLogin/{userID}")
    public String userLogin(@PathVariable(name = "userID") Integer userID,
                            @RequestParam(name = "userPassword") String userPassword,
                            @RequestParam(name = "userPassword1") String userPassword1,
                            @RequestParam(name = "userPassword2") String userPassword2,
                            HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("testBlogUserInfo");
        if (IsConnected(request) != null || !userID.equals(userInfo.getUserID())) {
            return IsConnected(request);
        }
        if (userPassword != null && !"".equals(userPassword) && userPassword1 != null && !"".equals(userPassword1) && userPassword2 != null && !"".equals(userPassword2)) {
            if (userLoginService.userLoginFindOne(userID).getUserPassword().equals(userPassword)) {
                if (userPassword1.equals(userPassword2)) {
                    UserLogin userLogin = new UserLogin();
                    userLogin.setUserID(userID);
                    userLogin.setUserPassword(userPassword1);
                    userLoginService.userLoginUpdate(userLogin);
                    request.getSession().removeAttribute("testBlogUserInfo");
                    return "redirect:/userLogin";
                }
            }
        }
        return "redirect:/Admin/main";
    }

    private String IsConnected(HttpServletRequest request) {
        return request.getSession().getAttribute("testBlogUserInfo") == null ? "redirect:/userLogin" : null;
    }

}
