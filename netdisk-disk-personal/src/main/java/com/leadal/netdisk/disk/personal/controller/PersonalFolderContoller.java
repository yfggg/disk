package com.leadal.netdisk.disk.personal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.disk.mapping.FolderMapping;
import com.leadal.netdisk.disk.model.Folder;
import com.leadal.netdisk.disk.service.IFolderService;
import com.leadal.netdisk.disk.view.FolderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 个人网盘空间表 前端控制器
 * </p>
 *
 * @author yf
 * @since 2022-04-07
 */
@Api(tags="个人网盘-文件夹-控制器")
@Slf4j
@RestController
@RequestMapping("/perdisk/folder")
public class PersonalFolderContoller {

    @Resource
    private IFolderService folderService;

    /**
     * 文件夹列表
     *
     * @param vo
     *
      diskId 网盘空间ID
      id 文件夹id 文件列表(进入文件夹）
     *
     * @return
     */
    @ApiOperation(value="文件夹列表")
    @GetMapping(value = "/query")
    public Result<?> query(FolderVO vo) {
        String diskId = vo.getDiskId();
        String folderId = vo.getId();
        QueryWrapper<Folder> folderQueryWrapper = new QueryWrapper<>();
        folderQueryWrapper
                .eq("parent_id", folderId)
                .eq("disk_id", diskId)
                .eq("del_flag", "0")
                .orderByAsc("create_time");
        List<Folder> folderTree = folderService.list(folderQueryWrapper);
//        List<Folder> folderTree = folderService.queryChildList(folderId, diskId);
        return Result.OK(folderTree);
    }

    /**
     * 新建文件夹
     *
     * @param vo
    {
    "diskId": "网盘空间ID",
    "name": "文件夹名称",
    "parentId": "上级文件夹ID",
    }
     * @return
     */
    @ApiOperation(value="新建文件夹")
    @PostMapping(value = "/create")
    public Result<?> create(@RequestBody FolderVO vo) {
        boolean result = folderService.save(FolderMapping.INSTANCE.toModel(vo));
        if(!result) { return Result.error("新建文件夹失败！"); }
        return Result.OK();
    }

    /**
     * 重命名文件夹
     *
     * @param vo
    {
    "diskId": "网盘空间ID",
    "name": "文件夹名称",
    "id": "文件夹id"
    }
     * @return
     */
    @ApiOperation(value="重命名文件夹")
    @PutMapping(value = "/rename")
    public Result<?> rename(@RequestBody FolderVO vo) {
        String diskId = vo.getDiskId();
        String id = vo.getId();
        QueryWrapper<Folder> folderQueryWrapper = new QueryWrapper<>();
        folderQueryWrapper
                .eq("disk_id", diskId)
                .eq("id", id)
                .eq("del_flag", "0");
        Folder folder = FolderMapping.INSTANCE.toModel(vo);
        boolean result = folderService.update(folder, folderQueryWrapper);
        if(!result) { return Result.error("文件夹重命名失败！"); }
        return Result.OK();
    }






}
