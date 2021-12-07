package com.springboot.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.common.entity.BaseEntity;
import com.springboot.demo.config.CacheConfig;
import com.springboot.demo.controller.request.UserQueryRequest;
import com.springboot.demo.controller.request.UserRequest;
import com.springboot.demo.dao.RoleMapper;
import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.dao.UserRoleMapper;
import com.springboot.demo.entity.Role;
import com.springboot.demo.entity.User;
import com.springboot.demo.entity.UserRole;
import com.springboot.demo.exception.DataExistException;
import com.springboot.demo.exception.DataNotExistException;
import com.springboot.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private CacheManager cacheManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


    /**
     * 分页查询用户信息
     * @param request
     * @return
     */
    public List<UserVo> listByPage(UserQueryRequest request){
        //
        LambdaQueryWrapper<User> query = new QueryWrapper<User>().lambda();
        query.like(User::getUserName, request.getUserName());

        Page<User> page = userMapper.selectPage(request.getPage(), query);
        List<UserVo> users = new ArrayList<>(page.getRecords().size());
        page.getRecords().forEach(item->{
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(item,userVo);
            //用户角色
            List<String> roles = this.roleMapper.findRolesByUserId(userVo.getId()).stream().map(Role::getRoleName).collect(Collectors.toList());
            userVo.setRoleNames(roles);
            users.add(userVo);
        });
        request.setPage(page);
        this.cacheManager.getCache("localhost").put("user",users);
        return users;
    }


    @Transactional(rollbackFor = Exception.class)
    public void createUser(UserRequest request){

        User user = new User();
        BeanUtils.copyProperties(request,user);
        //验证用户名
        List<User> users = this.userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getUserAccount, user.getUserAccount()));
        if(!CollectionUtils.isEmpty(users)) {
            throw new DataExistException("用户名已存在");
        }
        //验证角色
        List<Role> roles = this.roleMapper.selectList(new QueryWrapper<Role>().in("id",request.getRoles()));
        if (request.getRoles().size() != roles.size()) {
            throw new DataNotExistException("角色不存在,请检查参数");
        }
        //新增用户
        this.userMapper.insert(user);
        //新增用户角色
        this.resetUserRole(roles,user.getId());

    }


    /**
     * 用户详情
     * @param id
     * @return
     */
    @Cacheable(value = CacheConfig.detaultCache)
    public UserVo getUserDetail(Integer id){
        User user = this.userMapper.selectById(id);
        Assert.notNull(user,"用户不存在");
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        //用户角色
        List<Integer> roles = this.roleMapper.findRolesByUserId(userVo.getId()).stream().map(BaseEntity::getId).collect(Collectors.toList());
        userVo.setRoles(roles);

        return userVo;
    }


    /**
     * 修改用户信息
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserRequest request){

        //验证用户是否存在
        User user = this.userMapper.selectById(request.getId());
        if(null == user) {
            throw new DataNotExistException("用户不存在");
        }
        //验证角色
        List<Role> roles = this.roleMapper.selectList(new QueryWrapper<Role>().lambda().in(Role::getId,request.getRoles()));
        if (request.getRoles().size() != roles.size()) {
            throw new DataNotExistException("角色不存在,请检查参数");
        }
        BeanUtils.copyProperties(request,user);
        //更新用户
        this.userMapper.updateById(user);
        //更新用户角色，先删除用户所有角色
        this.userRoleMapper.delete(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId,user.getId()));
        this.resetUserRole(roles,user.getId());

    }

    /**
     * 删除用户
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer userId){
        this.userMapper.deleteById(userId);
    }


    /**
     * 重置用户角色
     * @param roles
     * @param userId
     */
    public void resetUserRole(List<Role> roles,Integer userId){

        // TODO-XHY: 2021/9/29 批量新增
        roles.forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(role.getId());
            this.userRoleMapper.insert(userRole);
        });

    }



}
