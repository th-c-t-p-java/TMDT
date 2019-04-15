package com.web.demo.mapper;

import com.web.demo.model.CartItem;
import com.web.demo.model.CartItemExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartItemMapper {
	List<CartItem> selectByCustomerId(Integer customerId);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    long countByExample(CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    int deleteByExample(CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    int insert(CartItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    int insertSelective(CartItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    List<CartItem> selectByExample(CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    CartItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    int updateByExampleSelective(@Param("record") CartItem record, @Param("example") CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    int updateByExample(@Param("record") CartItem record, @Param("example") CartItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    int updateByPrimaryKeySelective(CartItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_item
     *
     * @mbg.generated Wed Apr 03 16:56:42 ICT 2019
     */
    int updateByPrimaryKey(CartItem record);
    
    int updateQuantityById(@Param("id")Integer id,@Param("amount") long amount,@Param("quantity") Integer quantity);
    
    int updateIsEnableById(@Param("id") Integer id);
    
}