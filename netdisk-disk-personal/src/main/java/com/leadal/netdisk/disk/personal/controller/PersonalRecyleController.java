package com.leadal.netdisk.disk.personal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.disk.enums.FileKind;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.personal.service.IPersonalDiskService;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.disk.service.IOwnerService;
import com.leadal.netdisk.disk.view.FileVO;
import com.leadal.netdisk.recyle.model.Recyle;
import com.leadal.netdisk.recyle.service.IRecyleService;
import com.leadal.netdisk.resource.service.IResourceService;
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
 * 回收站表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-05-05
 */
@Api(tags="个人网盘-回收站-控制器")
@Slf4j
@RestController
@RequestMapping("/perdisk/recyle")
public class PersonalRecyleController {

    private final static String diskId = "1";

    @Resource
    private IRecyleService recyleService;

    @Resource
    private IPersonalDiskService personalDiskService;


    /**
     * 分页查询
     *
     * @param pageNo    当前页
     * @param pageSize  每页显示数据量
     * @return
     */
    @ApiOperation(value="分页查询")
    @GetMapping(value = "/query")
    public Result<?> selectList(
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

        QueryWrapper<Recyle> wrapper = new QueryWrapper<>();
        wrapper
                .eq("recyle.disk_id", diskId)
                .eq("recyle.del_flag", "0")
                .orderByAsc("recyle.create_time");

        Page<File> page = new Page<>(pageNo, pageSize);
        IPage<File> pageFiles = recyleService.selectPage(page, wrapper);

        return Result.ok(pageFiles);
    }

    /**
     * 加入回收站
     *
     * {
     *   "fileIds": [
     *     "文件id"
     *   ]
     * }
     * @return
     */
    @ApiOperation(value="加入回收站")
    @PostMapping(value = "/creat")
    public Result<?> create(@RequestBody FileVO vo) {

        List<String> fileIds = vo.getFileIds();

        boolean result = recyleService.create(fileIds, diskId);

        if(result) {
            return Result.error("加入回收站失败！");
        }

        return Result.ok();
    }

    /**
     * 还原
     *
     * {
     *   "fileIds": [
     *     "文件id"
     *   ]
     * }
     * @return
     */
    @ApiOperation(value="还原")
    @PostMapping(value = "/recover")
    public Result<?> recoverById(@RequestBody FileVO vo) {

        List<String> fileIds = vo.getFileIds();

        boolean result = recyleService.recover(fileIds, diskId);

        if(result) {
            Result.error("还原文件失败！");
        }

        return Result.ok();
    }

    /**
     * 清空回收站（全部）什么参数都不要传
     *
     * 清空回收站（指定单条或多条），如下
     * {
     *   "fileIds": [
     *     "文件id"
     *   ]
     * }
     * @return
     */
    @ApiOperation(value="清空回收站")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestBody FileVO vo) {

        List<String> fileIds;
        if(null != vo.getFileIds() && !"".equals(vo.getFileIds())) {
            fileIds = vo.getFileIds();
        } else {
            QueryWrapper<Recyle> wrapper = new QueryWrapper<>();
            wrapper
                    .eq("disk_id", diskId)
                    .eq("del_flag", "0");

            List<Recyle> recyles = recyleService.list(wrapper);
            fileIds = recyles.stream().map(recyle -> recyle.getFileId())
                    .collect(Collectors.toList());
        }

        if(0 < fileIds.size()) {
            Long sum = recyleService.delete(fileIds, diskId);

            personalDiskService.minusSpace(diskId, sum);
        }

        return Result.ok();
    }



}
