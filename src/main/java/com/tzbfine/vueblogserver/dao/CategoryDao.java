package com.tzbfine.vueblogserver.dao;


import com.tzbfine.vueblogserver.model.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryDao {

    @Select("select * from category ")
    List<Category> getCategoryList();

    @Select("select * from category where cateName = #{cateName}")
    int getCategoryByName(String cateName);

    @Select("select cateName from category where id = #{id}")
    String getCateNameById(int id);

    @Update("update category set cateName = #{cateName} where id = #{id]")
    int updateCateNameById(int id,String cateName);

    @Insert("insert into category(cateName,date) values(#{category},#{date})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int addCategoryName(Category category);
}
