package com.tzbfine.vueblogserver.Service;

import com.tzbfine.vueblogserver.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategoryList();
    String getCateNameById(int id);
    int updateCateNameById(int id,String cateName);
    int addCategory(Category category);
    int getCategoryByName(String cateName);
}
