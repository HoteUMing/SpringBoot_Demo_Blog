package Blog.Controller;

import Blog.Service.BlogDetailServiceImpl;
import Blog.entity.BlogDetail;
import Blog.entity.UserInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    BlogDetailServiceImpl blogDetailService;

    @GetMapping("/main")
    public String main(@RequestParam(name = "page", defaultValue = "1") int page, HttpServletRequest request) {
        if (IsConnected(request) != null) {
            return IsConnected(request);
        }
        List<BlogDetail> list = blogDetailService.userBlogFindAll(page, ((UserInfo) request.getSession().getAttribute("testBlogUserInfo")).getUserID());
        if (list.size() != 0) {
            PageInfo<BlogDetail> pageInfo = new PageInfo<>(list);
            request.setAttribute("pageInfo", pageInfo);
        }
        return "/Admin/main";
    }

    @GetMapping("/delete/{blogID}")
    public String delete(@PathVariable(name = "blogID") Integer blogID, HttpServletRequest request) {
        if (IsConnected(request) != null) {
            return IsConnected(request);
        }
        if (blogID != null) {
            BlogDetail blogDetail = blogDetailService.blogFindOne(blogID);
            if (blogDetail != null && blogDetail.getUserID().equals(((UserInfo) request.getSession().getAttribute("testBlogUserInfo")).getUserID())) {
                blogDetailService.blogDelete(blogID);
            }
        }
        return "redirect:/Admin/main";
    }

    @GetMapping("/alter/{blogID}")
    public String turnAlter(@PathVariable(name = "blogID") Integer blogID, HttpServletRequest request) {
        if (IsConnected(request) != null) {
            return IsConnected(request);
        }
        if (blogID != null) {
            BlogDetail blogDetail = blogDetailService.blogFindOne(blogID);
            if (blogDetail != null && blogDetail.getUserID().equals(((UserInfo) request.getSession().getAttribute("testBlogUserInfo")).getUserID())) {
                request.setAttribute("blogDetail", blogDetail);
                return "/Admin/alter";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/alter/{blogID}")
    public String alter(@PathVariable(name = "blogID") Integer blogID,
                        @RequestParam(name = "blogTitle") String blogTitle,
                        @RequestParam(name = "blogKeyword") String blogKeyword,
                        @RequestParam(name = "blogSummary") String blogSummary,
                        @RequestParam(name = "blogContent") String blogContent,
                        HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("testBlogUserInfo");
        if (IsConnected(request) != null) {
            return IsConnected(request);
        }
        BlogDetail blogDetail = blogDetailService.blogFindOne(blogID);
        if (blogDetail != null && blogDetail.getUserID().equals(userInfo.getUserID())) {
            blogDetail = new BlogDetail();
            blogDetail.setBlogID(blogID);
            blogDetail.setBlogTitle(blogTitle);
            blogDetail.setBlogKeyword(blogKeyword);
            blogDetail.setBlogSummary(blogSummary);
            blogDetail.setBlogContent(blogContent);
            blogDetail.setBlogModifyTime(System.currentTimeMillis());
            blogDetailService.blogUpdate(blogDetail);
        }
        return "redirect:/Admin/main";
    }

    private String IsConnected(HttpServletRequest request) {
        return request.getSession().getAttribute("testBlogUserInfo") == null ? "redirect:/userLogin" : null;
    }

}
