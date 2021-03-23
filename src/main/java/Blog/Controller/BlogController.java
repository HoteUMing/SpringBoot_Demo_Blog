package Blog.Controller;

import Blog.Service.BlogDetailServiceImpl;
import Blog.Service.UserInfoServiceImpl;
import Blog.entity.BlogDetail;
import Blog.entity.UserInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {

    @Autowired
    private BlogDetailServiceImpl blogDetailService;
    @Autowired
    private UserInfoServiceImpl userInfoService;

    @GetMapping("/")
    public String main(@RequestParam(name = "page", defaultValue = "1") int page, HttpServletRequest request) {
        request.setAttribute("pageName", "主页");
        List<BlogDetail> list = blogDetailService.blogFindAll(page);
        if (list.size() != 0) {
            PageInfo<BlogDetail> pageInfo = new PageInfo<>(list);
            Map<Integer, String> author = new HashMap<>();
            for (BlogDetail detail : list) {
                String userName = userInfoService.userInfoFindOne(detail.getUserID()).getUserName();
                if (userName != null) {
                    author.put(detail.getBlogID(), userName);
                } else {
                    author.put(detail.getBlogID(), "该帐号已注销");
                }
            }
            //System.out.println(pageInfo);
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("author", author);
        }
        return "/Blog/index";
    }

    @GetMapping("/Blog/detail/{blogID}")
    public String detail(@PathVariable(name = "blogID") Integer blogID, HttpServletRequest request) {
        request.setAttribute("pageName", "文章");
        if (blogID != null) {
            BlogDetail blogDetail = blogDetailService.blogFindOne(blogID);
            if (blogDetail != null) {
                request.setAttribute("blogDetail", blogDetail);
                String userName = userInfoService.userInfoFindOne(blogDetail.getUserID()).getUserName();
                if (userName != null) {
                    request.setAttribute("author", userName);
                } else {
                    request.setAttribute("author", "该帐号已注销");
                }
                return "/Blog/detail";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/Blog/publish")
    public String turnPublish(HttpServletRequest request) {
        request.setAttribute("pageName", "发布");
        return IsConnected(request) == null ? "/Blog/publish" : IsConnected(request);
    }

    @PostMapping("/Blog/publish")
    public String Publish(@RequestParam(name = "blogTitle") String blogTitle,
                          @RequestParam(name = "blogKeyword") String blogKeyword,
                          @RequestParam(name = "blogSummary") String blogSummary,
                          @RequestParam(name = "blogContent") String blogContent,
                          HttpServletRequest request) {
        if (IsConnected(request) != null) {
            return IsConnected(request);
        }
        BlogDetail blogDetail = new BlogDetail();
        blogDetail.setBlogTitle(blogTitle);
        blogDetail.setBlogKeyword(blogKeyword);
        blogDetail.setBlogSummary(blogSummary);
        blogDetail.setBlogContent(blogContent);
        blogDetail.setBlogCreateTime(System.currentTimeMillis());
        blogDetail.setBlogModifyTime(blogDetail.getBlogCreateTime());
        blogDetail.setUserID(((UserInfo) request.getSession().getAttribute("testBlogUserInfo")).getUserID());
        blogDetailService.blogInsert(blogDetail);
        return "redirect:/";
    }

    private String IsConnected(HttpServletRequest request) {
        return request.getSession().getAttribute("testBlogUserInfo") == null ? "redirect:/userLogin" : null;
    }

}
