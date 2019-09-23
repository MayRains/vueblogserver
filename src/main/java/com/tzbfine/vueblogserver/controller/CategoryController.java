package com.tzbfine.vueblogserver.controller;

import com.tzbfine.vueblogserver.Service.CategoryService;
import com.tzbfine.vueblogserver.Utils.AjaxResponse;
import com.tzbfine.vueblogserver.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping(value = "/getCategoryList", produces = "application/json")
    @ResponseBody
    public AjaxResponse getCategoryList() {
        List<Category> list = categoryService.getCategoryList();
        if (list != null) {
            return AjaxResponse.success(list, "获取分类成功");
        } else {
            return AjaxResponse.fail(null, "获取分类失败");
        }
    }

    @GetMapping(value = "/getCateNameById/{id}", produces = "application/json")
    @ResponseBody
    public AjaxResponse getCateNameById(@PathVariable int id) {
        String str = categoryService.getCateNameById(id);
        if (str != null) {
            return AjaxResponse.success(str, "查询分类名成功");
        } else {
            return AjaxResponse.fail(null, "查询分类名失败");
        }
    }

    @PutMapping(value = "/updateCateNameById", produces = "application/json")
    @ResponseBody
    public AjaxResponse updateCateNameById(int id, String cateName) {
        int res = categoryService.updateCateNameById(id, cateName);
        if (res != 1) {
            return AjaxResponse.fail(null, "更新失败");
        } else {
            return AjaxResponse.success(res, "更新成功");
        }
    }

    @PostMapping(value = "/addCategory", produces = "application/json")
    @ResponseBody
    public AjaxResponse addCategory(@RequestBody Category category) {
        int res = categoryService.getCategoryByName(category.getCateName());
        if (res != 1) {
            categoryService.addCategory(category);
            return AjaxResponse.success(category, "添加类别成功");
        } else {
            return AjaxResponse.fail(category, "有重复的类别名,添加类别失败");
        }
    }

}
