package Blog.Service;

import Blog.entity.BlogDetail;

import java.util.List;

public interface BlogDetailService {

    //查询所有文章
    List<BlogDetail> blogFindAll(Integer pageNum);

    //查询指定用户所有文章
    List<BlogDetail> userBlogFindAll(Integer userID,Integer pageNum);

    //查询指定文章
    BlogDetail blogFindOne(Integer blogID);

    //新增文章
    void blogInsert(BlogDetail blogDetail);

    //更新指定文章
    void blogUpdate(BlogDetail blogDetail);

    //删除指定文章
    void blogDelete(Integer blogID);

}
