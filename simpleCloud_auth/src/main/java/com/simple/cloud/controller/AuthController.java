package com.simple.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.cloud.entities.vo.LoginVo;
import com.simple.cloud.entities.SysRole;
import com.simple.cloud.entities.SysUser;
import com.simple.cloud.entities.vo.RouterVo;
import com.simple.cloud.service.SysMenuService;
import com.simple.cloud.service.SysUserService;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import com.simple.cloud.utils.securityHelper.JWTHelper;
import com.simple.cloud.utils.securityHelper.SHA_256Helper;
import com.simple.cloud.utils.securityHelper.SecurityAccessConstant;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Charles
 * @create 2024-05-10-16:55
 */

@RestController
@RequestMapping("/simple/cloud/access")
public class AuthController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public ResultData login(@RequestBody LoginVo loginVo) throws Exception{
        // 先根据email找指定用户
        SysUser sysUser = sysUserService.findUserByEmail(loginVo.getEmail());
        if(sysUser == null)
            throw new Exception("找不到该用户");

        //加密密码来比较
        String encryptValue = SHA_256Helper.encrypt(loginVo.getPassword());
        if(!StringUtils.pathEquals(encryptValue,sysUser.getPassword()))
            throw new Exception("密码错误");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+encryptValue);

        //获取用户的角色
        List<SysRole> sysRoles = sysUserService.selectAllByUserId(sysUser.getId());
        sysUser.setRoleList(sysRoles);

        //根据id获取所有菜单列表
        List<RouterVo> routerList = sysMenuService.getAllRouterListByUserId(sysUser.getId());

        //根据id获取所有按钮列表
        List<String> permsList = sysMenuService.getAllMenuListByUserId(sysUser.getId());

        //map中插入相应的值
        Map<String, Object> map = new HashMap<>();
        map.put("routers",routerList);
        map.put("buttons",permsList);
        map.put("roles",sysUser.getRoleList());
        map.put("name",sysUser.getName());

        //存放token到请求头中
        String[] tokenArray = JWTHelper.createToken(sysUser.getId(), sysUser.getEmail(), permsList);
        map.put("token",tokenArray[0]);
        map.put("tokenExpire",JWTHelper.getExpirationDate(tokenArray[0]).getTime());
        map.put("refreshToken",tokenArray[1]);

        // 存放用户信息权限数据
        redisTemplate.opsForValue().set(sysUser.getId() + SecurityAccessConstant.USERINFO_REDIS_STORAGE_KEY
                                    , new ObjectMapper().writeValueAsString(map)
                                    , 30*60, TimeUnit.SECONDS);
        // 存放refreshToken
        redisTemplate.opsForValue().set(tokenArray[0]
                                    , tokenArray[1]
                                    , JWTHelper.getExpirationDate(tokenArray[1]).getTime() , TimeUnit.MILLISECONDS);

        return ResultData.success(map);
    }

    @GetMapping("/refresh")
    public ResultData refresh(HttpServletRequest request){
        String refreshToken = JWTHelper.getToken(request.getHeader(SecurityAccessConstant.HEADER_NAME_TOKEN));

        //刷新token
        String refresh = JWTHelper.refresh(refreshToken);

        Map<String, Object> map = new HashMap<>();
        map.put("token",refresh);
        map.put("expire",JWTHelper.getExpirationDate(refresh).getTime());
        return ResultData.success(map);
    }

    /**
     * 获取用户信息
     * @return
     */
    @PostMapping("/info")
    public ResultData info(HttpServletRequest request) throws JsonProcessingException {
        String token = JWTHelper.getToken(request.getHeader(SecurityAccessConstant.HEADER_NAME_TOKEN));
        Long userId = JWTHelper.getUserId(token);

        if(userId == null)
            return ResultData.fail(ResultCodeEnum.RC401.getCode(), "token失效请重新登录");

        // 存放权限信息到redis中 ， springsecurity通过 userId 做为key获取权限列表
        String storageJSON = (String) redisTemplate.opsForValue()
                .get(userId + SecurityAccessConstant.USERINFO_REDIS_STORAGE_KEY);

        if(null == storageJSON)
            return ResultData.fail(ResultCodeEnum.RC401.getCode(), "登录失败请重新登录");

        return ResultData.success(new ObjectMapper().readValue(storageJSON , Map.class));
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public ResultData logout(HttpServletRequest request){
        String token = JWTHelper.getToken(request.getHeader(SecurityAccessConstant.HEADER_NAME_TOKEN));
        if(token == null)
            return ResultData.success("token失效 以退出登录");

        Long userId = JWTHelper.getUserId(token);
        if(userId == null)
            return ResultData.success("token失效 以退出登录");

        redisTemplate.delete(userId + SecurityAccessConstant.USERINFO_REDIS_STORAGE_KEY);
        redisTemplate.delete(userId + SecurityAccessConstant.REFRESH_TOKEN_REDIS_STORAGE_KEY);
        return ResultData.success("退出成功");
    }
}
