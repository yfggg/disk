package com.leadal.netdisk.disk.personal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.model.AllFileResults;
import com.leadal.netdisk.disk.model.FileResults;
import com.leadal.netdisk.disk.model.Folder;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.disk.service.IFolderService;
import com.leadal.netdisk.disk.view.FolderVO;
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

    @Resource
    private IFolderService folderService;

    @Resource
    private IFileService fileService;


    /**
     *  搜索 同时搜索出文件和文件夹 只从全部文件开始搜索
     *
     * @param keyword 关键字
     * @return
     */
    @ApiOperation(value="关键字搜索")
    @GetMapping(value = "/query/{keyword}")
    public Result<?> keywordQuery(@PathVariable String keyword) {
        QueryWrapper<Folder> folderQueryWrapper = new QueryWrapper<>();
        folderQueryWrapper.like("name", keyword);
        List<Folder> folders = folderService.list(folderQueryWrapper);

        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper.like("name", keyword);
        List<File> files = fileService.list(fileQueryWrapper);

        FileResults fileResults = new FileResults(folders, files);
        Integer total = folders.size() + files.size();

        return Result.OK(total, fileResults);
    }

    /**
     * 文件列表(进入文件夹)
     *
     * @param vo
     *
        diskId 网盘空间ID
        id 文件夹id 文件列表(进入文件夹）

     * @return
     */
    @ApiOperation(value="文件列表(进入文件夹)")
    @GetMapping(value = "/query")
    public Result<?> query(FolderVO vo) {
        String diskId = vo.getDiskId();
        String folderId = vo.getId();

        List<Folder> folderTree = folderService.selectList(folderId, diskId);
        List<Folder> folders = folderTree.stream().filter(folder ->
                folder.getParentId().equals(folderId)).collect(Collectors.toList());
        // 取出级联路径
        List<Folder> parentPath = folderTree.stream().filter(folder ->
                !folder.getParentId().equals(folderId)).collect(Collectors.toList());

        // 查询级联路径下的文件
        QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper
                .eq("disk_id", diskId)
                .eq("folder_id", folderId)
                .orderByAsc("create_time");
        List<File> files = fileService.list(fileQueryWrapper);

        AllFileResults allFileResults = new AllFileResults(parentPath, new FileResults(folders, files));
        Integer total = folders.size() + files.size();

        return Result.OK(total, allFileResults);
    }





}
