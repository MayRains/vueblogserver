package com.tzbfine.vueblogserver.Service.Imp;

import com.tzbfine.vueblogserver.Service.CategoryService;
import com.tzbfine.vueblogserver.dao.CategoryDao;
import com.tzbfine.vueblogserver.model.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Resource
    CategoryDao categoryDao;

    @Override
    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    @Override
    public String getCateNameById(int id) {
        return categoryDao.getCateNameById(id);
    }

    @Override
    public int updateCateNameById(int id, String cateName) {
        return categoryDao.updateCateNameById(id,cateName);
    }

    @Override
    public int addCategory(Category category) {
        category.setId(category.getId());
        category.setCateName(category.getCateName());
        category.setDate(category.getDate());
        return categoryDao.addCategoryName(category);
    }

    @Override
    public int getCategoryByName(String cateName) {
        return categoryDao.getCategoryByName(cateName);
    }
}
