package com.leadal.netdisk.disk.personal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 个人网盘空间表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Api(tags="个人网盘-控制器")
@Slf4j
@RestController
@RequestMapping("/perdisk/disk")
public class PersonalDiskController {


    @ApiOperation(value="关键字搜索")
    @GetMapping(value = "/query2")
    public Result<?> keywordQuery() {
        return Result.OK();
    }

    @ApiOperation(value="文件列表(进入文件夹)")
    @GetMapping(value = "/query")
    public Result<?> query() {
        return Result.OK();
    }



}
