package com.app.Dao;

import com.pojo.TSysRole;
import java.util.List;

public interface TSysRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role
     *
     * @mbg.generated
     */
    int insert(TSysRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role
     *
     * @mbg.generated
     */
    TSysRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role
     *
     * @mbg.generated
     */
    List<TSysRole> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TSysRole record);
}