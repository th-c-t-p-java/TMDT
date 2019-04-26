package com.web.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    
    private Integer id;
  
    private Object catalogId;
  
    private String productName;
  
    private BigDecimal price;
    
    private Integer discount;
  
    private Object image;

    private Integer quantity;

    private Integer view;

    private Integer insertUser;

    private Date insertDate;

    private Integer modifyUser;

    private Date modifyDate;

    private Boolean isEnable;

    private Object description;

    public Integer getCata() {
    	Integer[] ctg = (Integer[])catalogId;
    	return ctg[0];
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Object catalogId) {
        this.catalogId = catalogId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
   
    public String getImage() {
        String[] tmp = (String[])image;
    	return tmp[0];
    }
    public String[] getImageList() {
    	return (String[])image;
    }
   
    public void setImage(Object image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Integer getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Integer modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Object getDescription() {
        return description;
    }
    
    public void setDescription(Object description) {
        this.description = description;
    }
}