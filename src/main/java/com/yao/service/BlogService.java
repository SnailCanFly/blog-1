package com.yao.service;

import com.yao.po.Blog;
import com.yao.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);
    Blog getAndConvert(Long id);
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> lisQueryBlog(Pageable pageable,String query);

    Blog saveBlog(Blog blog);
    List<Blog>  listRecommendTopBlog(Integer size);
    Blog updateBlog(Long id,Blog blog);

    Map<String,List<Blog>> archiveBlog();
    Long countBlog();


    void deleteBlog(Long id);

}
