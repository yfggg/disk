package com.leadal.netdisk.disk.personal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.model.FileResult;
import com.leadal.netdisk.disk.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


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

    private static final String DISK_ID = "1";

    @Resource
    private IFileService fileService;

    /**
     * 搜索 同时搜索出文件和文件夹 只从全部文件开始搜索
     *
     * @param keyword 关键字
     * @return
     */
    @ApiOperation(value="关键字搜索")
    @GetMapping(value = "/queryKeyword")
    public Result<?> keywordQuery( String keyword) {

        QueryWrapper<File> fWrapper = new QueryWrapper<>();
        fWrapper
                .like("folder_name", keyword)
                .or()
                .like("file_name", keyword)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");
        List<File> files = fileService.list(fWrapper);

        // 数量
        int total = files.size();

        return Result.OK(total, files);
    }

    /**
     * 文件列表(进入文件夹)
     *
     * @param id 文件夹ID
     * @return
     */
    @ApiOperation(value="文件列表(进入文件夹)")
    @GetMapping(value = "/query")
    public Result<?> query(String id) {

        // 文件和文件夹
        QueryWrapper<File> eqWrapper = new QueryWrapper<>();
        eqWrapper
                .and(wrapper ->
                    wrapper
                        .eq("table_kind", TableKind.FOLDER)
                        .eq("folder_parent_id", id)
                )
                .or(wrapper ->
                    wrapper
                        .eq("table_kind", TableKind.FILE)
                        .and(wrapper1 ->
                            wrapper1.likeLeft("folder_parent_ids", id)
                        )

                )
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");
        List<File> files = fileService.list(eqWrapper);

        // 级联路径
        QueryWrapper<File> likeWrapper = new QueryWrapper<>();
        likeWrapper
                .likeLeft("folder_parent_ids", id)
                .eq("disk_id", DISK_ID)
                .eq("table_kind", TableKind.FOLDER)
                .eq("del_flag", "0");
        File file = fileService.getOne(likeWrapper);
        String[] folderParentIds = file.getFolderParentIds().split(",");
        QueryWrapper<File> inWrapper = new QueryWrapper<>();
        inWrapper
                .in("id", folderParentIds)
                .eq("disk_id", DISK_ID)
                .eq("table_kind", TableKind.FOLDER)
                .eq("del_flag", "0")
                .orderByAsc("create_time");
        List<File> pathTree = fileService.list(inWrapper);

        FileResult fileResult = new FileResult(files, pathTree);

        // 数量
        int total = files.size();

        return Result.OK(total, fileResult);
    }


}
