package com.web.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class CartItem {
	public CartItem()
	{
		this.amount= new BigDecimal(10000);
		this.customerId= null;
		this.insertDate=null;
		this.insertUser=null;
		this.isEnable=false;
		this.modifyDate= null;
		this.modifyUser= null;
		this.productId= null;
		this.quantity= null;		
		
	}
	    
    private Integer id;

    private Integer customerId;

    private Integer productId;   

    private Integer quantity;

    private BigDecimal amount;

    private Integer insertUser;
   
    private Date insertDate;

    private Integer modifyUser;

    private Date modifyDate;

    
    private Boolean isEnable;
    
    String productName;
    
    Object image;
    
    public void setProductName(String productName)
    {
    	this.productName = productName;
    	
    }
    public String getProductName()
    {
    	return this.productName;
    }
    
    public void setImage(Object image)
    {
    	this.image = image;
    }
    
    public Object getImage()
    {
    	if(image instanceof String[])
    	{
    		String[] tmp = (String[]) image;
        	return tmp[0]; 
    	}else
    	return image;
    }
    public Integer getId() {
        return id;
    }

   
    public void setId(Integer id) {
        this.id = id;
    }

   
    public Integer getCustomerId() {
        return customerId;
    }

    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


   
    public Integer getProductId() {
        return productId;
    }

    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    
    public Integer getQuantity() {
        return quantity;
    }

   
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

   
    public Double getAmount() {
        return amount.doubleValue();
    }

    
    public void setAmount(BigDecimal amount) {
    	
        this.amount = amount;
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
}