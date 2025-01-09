package com.example.system.repository.blog;

import com.example.system.model.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE b.blogType = :blogType")
    List<Blog> findAllBlogByType(@Param("blogType") int blogType);

}
