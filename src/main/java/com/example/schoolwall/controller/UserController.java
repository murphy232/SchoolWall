package com.example.schoolwall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.schoolwall.entity.Respon;
import com.example.schoolwall.entity.User;
import com.example.schoolwall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //接收前段的请求，默认会将返回的对象数据转换成JSON格式
@RequestMapping("/user")
public class UserController {

    @Autowired   //spring的功能,自动把UserMapper实例化出来的对象注入注入userMapper
    private UserMapper userMapper;

//    @GetMapping("/query")
//    public List query(){
//       List<User> list =  userMapper.find();
//       System.out.println(list);
//        return list;
//    }
//    @PostMapping("insert")
//    public String insert(User user){
//        int cnt = userMapper.insert(user);
//        if(cnt>0){
//            return "插入成功";
//        } else return "插入失败";
//    }

    @GetMapping("/query")  //管理员查询所有用户
    public List query(){

        List<User> list =  userMapper.selectList(null);
//        System.out.println(list);
        return list;
    }

    @GetMapping("/delete") //管理员根据用户id删除该用户
    public Respon delete(User user){
        Respon respon = new Respon();
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.eq("uid",idd);
        respon.setRes(userMapper.del(user.getUid()));  //deleteById不是
        return respon;
    }

    @PostMapping("insert")   //用户注册，插入用户信息，管理员添加用户
    public Respon insert(User user){
        Respon respon = new Respon();
        int cnt = userMapper.insert(user);
        if(cnt>0){
            respon.setRes(1);
        } else respon.setRes(0);
        return respon;
    }


    //对于忘记密码，可通过输入基本信息等方式来重置密码。
    @PostMapping("reset")
    public Respon reset(int id,String name,String mail,String pwd){
        int cnt = userMapper.update(id,name,mail,pwd);
        Respon respon = new Respon();
        respon.setRes(cnt);
        return respon;
    }


}
