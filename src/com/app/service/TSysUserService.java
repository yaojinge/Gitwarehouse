package com.app.service;

import com.app.Dao.TSysUserMapper;
import com.pojo.TSysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TSysUserService implements UserDetailsService {
    @Autowired
    private TSysUserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TSysUser tSysUser=userMapper.selectUsername(username);
               if (tSysUser==null){
                   throw  new UsernameNotFoundException("用户名不存在");
               }
        return tSysUser;
    }
}
