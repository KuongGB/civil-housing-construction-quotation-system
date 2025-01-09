package com.example.system.service.blog;

import com.example.system.dto.blog.BlogDto;
import com.example.system.model.blog.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getListBlog();

    Blog getBlogById(Long id);

    boolean createBlog(BlogDto blog);

    List<Blog> getListBlogByType(int blogType);

    boolean updateBlog(Long blogId,BlogDto blog);
}
