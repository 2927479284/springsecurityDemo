package com.ddbh.service;

import com.ddbh.domain.dto.UserLoginDto;
import com.ddbh.utils.AjaxResult;
import org.springframework.stereotype.Service;

/**
 * 用户登录业务层
 */

public interface LoginUserService {
    AjaxResult login(UserLoginDto loginDto);
}
