package Blog.Service;

import Blog.Dao.BlogDetailMapper;
import Blog.entity.BlogDetail;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogDetailServiceImpl implements BlogDetailService {

    @Autowired
    private BlogDetailMapper blogDetailMapper;

    @Override
    public List<BlogDetail> blogFindAll(Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<BlogDetail> list = blogDetailMapper.blogFindAll();
        //for (BlogDetail blogDetail : list) System.out.println(blogDetail);
        return list;
    }

    @Override
    public List<BlogDetail> userBlogFindAll(Integer userID, Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<BlogDetail> list = blogDetailMapper.userBlogFindAll(userID);
        //for (BlogDetail blogDetail : list) System.out.println(blogDetail);
        return list;
    }

    @Override
    public BlogDetail blogFindOne(Integer blogID) {
        BlogDetail blogDetail = blogDetailMapper.blogFindOne(blogID);
        //System.out.println(blogDetail);
        return blogDetail;
    }

    @Override
    public void blogInsert(BlogDetail blogDetail) {
        if (blogDetailMapper.blogFindOne(blogDetail.getBlogID()) == null) {
            blogDetailMapper.blogInsert(blogDetail);
        } else {
            System.out.println("文章已存在，无法创建");
        }
    }

    @Override
    public void blogUpdate(BlogDetail blogDetail) {
        if (blogDetailMapper.blogFindOne(blogDetail.getBlogID()) != null) {
            blogDetailMapper.blogUpdate(blogDetail);
        } else {
            System.out.println("文章不存在，无法修改");
        }
    }

    @Override
    public void blogDelete(Integer blogID) {
        if (blogDetailMapper.blogFindOne(blogID) != null) {
            blogDetailMapper.blogDelete(blogID);
        } else {
            System.out.println("文章不存在，无法删除");
        }
    }
}
