package Blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("Blog.Dao")
public class TestBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestBlogApplication.class, args);
    }

}
