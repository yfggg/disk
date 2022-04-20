//package com.leadal.netdisk.event.impl;
//
//import com.leadal.cas.core.event.SSOEvent;
//import com.leadal.cas.core.model.User;
//import com.leadal.netdisk.utils.SpringContextUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class SSOEventImpl implements SSOEvent {
//
//    @Override
//    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain, User user) throws IOException, ServletException {
//        log.info("start token config");
////        UserService userService = (UserService) SpringContextUtils.getBean("userService");
////        UserRelationService userRelationService=(UserRelationService) SpringContextUtils.getBean("userRelationService");
////        UserRelation userRelation=userRelationService.getOne(new QueryWrapper<UserRelation>().eq("refId",user.getId()));
////        com.leadal.doconvert.console.model.User user1= userService.getById(userRelation.getUserId());
////        Cookie cookie1=new Cookie("Admin-UserId",userRelation.getUserId());
////        Cookie cookie2=new Cookie("Admin-UserType",user1.getLoginType());
////        System.out.println("-----------------------");
////        System.out.println(userRelation.getId());
////        System.out.println(user1.getLoginType());
////        httpServletResponse.addCookie(cookie1);
////        httpServletResponse.addCookie(cookie2);
////        httpServletResponse.sendRedirect("http://doconvert.leadal.com/#/switch_task"+"?userId="+userRelation.getUserId()+"&userType="+user1.getLoginType());
////        httpServletResponse.addCookie(cookie1);
////        httpServletResponse.addCookie(cookie2);
//    }
//
//    @Override
//    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//    }
//
//
//}
