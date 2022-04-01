package com.leadal.netdisk.personal.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-04-01
 */
@Api(tags="用户表")
@Slf4j
@RestController
@RequestMapping("/personal")
public class PersonalController {

    @ApiOperation(value="分页查询")
    @GetMapping(value = "/queryPage")
    public void queryPage(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

    }

    @ApiOperation(value="新增")
    @PostMapping(value = "/insert")
    public void insert() {

    }

    @ApiOperation(value="更新")
    @PostMapping(value = "/updateById")
    public void updateById(@RequestBody String id) {

    }

    @ApiOperation(value="详情")
    @GetMapping(value = "/detailsById")
    public void detailsById(String id) {

    }

    @ApiOperation(value="逻辑删除")
    @PostMapping(value = "/deleteById")
    public void deleteById(@RequestBody String id) {

    }

}
