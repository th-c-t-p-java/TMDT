package com.web.demo.mapper;

import com.web.demo.model.AccountType;
import com.web.demo.model.AccountTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    long countByExample(AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    int deleteByExample(AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    int insert(AccountType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    int insertSelective(AccountType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    List<AccountType> selectByExample(AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    AccountType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    int updateByExampleSelective(@Param("record") AccountType record, @Param("example") AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    int updateByExample(@Param("record") AccountType record, @Param("example") AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    int updateByPrimaryKeySelective(AccountType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_type
     *
     * @mbg.generated Sat Mar 23 15:08:44 ICT 2019
     */
    int updateByPrimaryKey(AccountType record);
}