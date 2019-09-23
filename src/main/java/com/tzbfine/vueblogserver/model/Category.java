package com.tzbfine.vueblogserver.model;


public class Category {

  private long id;
  private String cateName;
  private java.sql.Date date;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCateName() {
    return cateName;
  }

  public void setCateName(String cateName) {
    this.cateName = cateName;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Category{" +
            "id=" + id +
            ", cateName='" + cateName + '\'' +
            ", date=" + date +
            '}';
  }
}
