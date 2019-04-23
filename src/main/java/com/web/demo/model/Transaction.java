package com.web.demo.model;

import java.util.Date;

public class Transaction {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.id
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.status
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.user_id
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.amount
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private Double amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.payment
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private String payment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.payment_info
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private String paymentInfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.message
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.sercurity
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private String sercurity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.insert_date
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private Date insertDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.modify_date
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    private Date modifyDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.address
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
 
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.id
     *
     * @param id the value for transaction.id
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.status
     *
     * @return the value of transaction.status
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.status
     *
     * @param status the value for transaction.status
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.user_id
     *
     * @return the value of transaction.user_id
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.user_id
     *
     * @param userId the value for transaction.user_id
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.amount
     *
     * @return the value of transaction.amount
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.amount
     *
     * @param amount the value for transaction.amount
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.payment
     *
     * @return the value of transaction.payment
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public String getPayment() {
        return payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.payment
     *
     * @param payment the value for transaction.payment
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.payment_info
     *
     * @return the value of transaction.payment_info
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.payment_info
     *
     * @param paymentInfo the value for transaction.payment_info
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.message
     *
     * @return the value of transaction.message
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.message
     *
     * @param message the value for transaction.message
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.sercurity
     *
     * @return the value of transaction.sercurity
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public String getSercurity() {
        return sercurity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.sercurity
     *
     * @param sercurity the value for transaction.sercurity
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setSercurity(String sercurity) {
        this.sercurity = sercurity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.insert_date
     *
     * @return the value of transaction.insert_date
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.insert_date
     *
     * @param insertDate the value for transaction.insert_date
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transaction.modify_date
     *
     * @return the value of transaction.modify_date
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transaction.modify_date
     *
     * @param modifyDate the value for transaction.modify_date
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}