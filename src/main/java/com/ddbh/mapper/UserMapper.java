package com.ddbh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddbh.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}