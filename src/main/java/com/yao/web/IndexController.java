package com.yao.web;

import com.yao.NotFoundException;
import com.yao.service.BlogService;
import com.yao.service.TagService;
import com.yao.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.findTop(6));
        model.addAttribute("tags", tagService.findTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendTopBlog(8));
        return "index";
    }

    @PostMapping("/search")
    public String seachBlog(String query, @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        model.addAttribute("page", blogService.lisQueryBlog(pageable, "%" + query + "%"));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String detailBlog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlog(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendTopBlog(3));
        return "_fragments :: newblogList";
    }


}
