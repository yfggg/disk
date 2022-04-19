package com.leadal.netdisk.disk.controller;


import com.leadal.netdisk.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-04-18
 */
@Api(tags="文件表")
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @ApiOperation(value="分页查询")
    @GetMapping(value = "/files")
    public Result<?> selectList(
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

        return Result.ok();
    }

    @ApiOperation(value="新增和更新")
    @PostMapping(value = "/file")
    public Result<?> createAndUpdate() {

        return Result.ok();
    }

    @ApiOperation(value="详情")
    @GetMapping(value = "/file/{id}")
    public Result<?> getById(@PathVariable String id) {

        return Result.ok();
    }

    @ApiOperation(value="逻辑删除")
    @DeleteMapping(value = "/file/{id}")
    public Result<?> deleteById(@PathVariable String id) {

        return Result.ok();
    }

}
