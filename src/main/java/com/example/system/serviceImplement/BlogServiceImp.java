package com.example.system.serviceImplement;

import com.example.system.dto.blog.BlogDto;
import com.example.system.model.blog.Blog;
import com.example.system.model.user.User;
import com.example.system.repository.blog.BlogRepository;
import com.example.system.repository.user.UserRepository;
import com.example.system.service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImp implements BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Override
    public List<Blog> getListBlog() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean createBlog(BlogDto blog) {
        try {
            User user = userRepository.findByEmail(blog.getUserEmail()).orElseThrow();
            Blog newBlog = new Blog();
            newBlog.setBlogName(blog.getBlogName());
            newBlog.setBlogContent(blog.getBlogContent());
            newBlog.setBlogType(blog.getBlogType());
            newBlog.setCreateDay(LocalDate.now());
            newBlog.setUser(user);
            newBlog.setImgPath(blog.getImgPath());
            blogRepository.save(newBlog);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Blog> getListBlogByType(int blogType) {
        return blogRepository.findAllBlogByType(blogType);
    }

    @Override
    public boolean updateBlog(Long blogId, BlogDto blog) {
        try {
            User user = userRepository.findByEmail(blog.getUserEmail()).orElseThrow();
            Blog updateBlog = blogRepository.findById(blogId).orElseThrow();
            updateBlog.setBlogName(blog.getBlogName());
            updateBlog.setImgPath(blog.getImgPath());
            updateBlog.setBlogContent(blog.getBlogContent());
            updateBlog.setBlogType(blog.getBlogType());
            updateBlog.setUser(user);
            blogRepository.save(updateBlog);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
