//package com.leadal.netdisk.controller;
//
//
//import com.leadal.cas.core.event.SSOEvent;
//import com.leadal.netdisk.common.model.Result;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//
///**
// * @author yf
// * @since 2022-04-07
// */
//@Api(tags="登录控制器")
//@Slf4j
//@RestController
//public class LoginController {
//
//    @Resource
//    private SSOEvent ssoEvent;
//
//    @ApiOperation(value="登录")
//    @GetMapping(value = "/login")
//    public Result<?> login() {
//
//        return Result.OK();
//    }
//
//
//
//
//}
