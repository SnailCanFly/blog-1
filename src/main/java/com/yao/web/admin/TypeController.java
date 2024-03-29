package com.yao.web.admin;

import com.yao.po.Type;
import com.yao.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page", typeService.listType(pageable));

        return "/admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }
    @PostMapping("/types")
    public String postType(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t=typeService.saveType(type);
        if ( t == null ){
        attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
    return "redirect:/admin/types";

    }

    /**
     * 删除类型Type
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String deleType(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }
    /**
     * 编辑类型Type
     * /admin/types/{id}/input(id=${type.id}
     */
    @PostMapping("/types/{id}")
    public String EditType(@Valid Type type,BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Type t=typeService.getTypeByName(type.getName());
        if (t != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type type1= typeService.updateType(id,type);
        if (type1==null){
            attributes.addFlashAttribute("message","更新失败");

        }else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }
}
