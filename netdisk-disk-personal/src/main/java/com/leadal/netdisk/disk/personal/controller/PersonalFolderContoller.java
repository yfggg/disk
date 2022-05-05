package com.leadal.netdisk.disk.personal.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leadal.netdisk.common.model.Result;
import com.leadal.netdisk.common.util.file.DateUtils;
import com.leadal.netdisk.disk.enums.TableKind;
import com.leadal.netdisk.disk.mapping.FileMapping;
import com.leadal.netdisk.disk.model.File;
import com.leadal.netdisk.disk.service.IFileService;
import com.leadal.netdisk.disk.view.FolderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
@Api(tags="个人网盘-文件夹-控制器")
@Slf4j
@RestController
@RequestMapping("/perdisk/folder")
public class PersonalFolderContoller {

    private static final String DISK_ID = "1";

    @Resource
    private IFileService fileService;

    /**
     * 文件夹列表
     *
     * @param id "文件夹ID"
     * @return
     */
    @ApiOperation(value="文件夹列表")
    @GetMapping(value = "/query")
    public Result<?> query(String id) {

        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper
                .eq("folder_parent_id", id)
                .eq("table_kind", TableKind.FOLDER)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");
        List<File> list = fileService.list(wrapper);

        return Result.OK(list);
    }

    /**
     * 新建文件夹
     *
     * {
     *   "id": "文件夹ID",
     *   "folderParentIds": "上级文件夹ID集",
     *   "folderName": "文件夹名称"
     * }
     * @param vo
     * @return
     */
    @ApiOperation(value="新建文件夹")
    @PostMapping(value = "/create")
    public Result<?> create(@RequestBody FolderVO vo) {

        String id = IdUtil.simpleUUID();
        String folderParentId = vo.getId();
        String folderParentIds = vo.getFolderParentIds();
        String folderName = vo.getFolderName();

        // 文件夹重名
        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper
                .eq("folder_name", folderName)
                .eq("table_kind", TableKind.FOLDER)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");
        int same = fileService.count(wrapper);
        if(0 < same) {
            folderName =
                    new StringBuffer().append(folderName).append(DateUtils.dateTimePath()).toString();
        }

        // 拼接级联路径
        String parentIdsBuffer =
                new StringBuffer().append(folderParentIds).append(",").append(id).toString();

        File folder = new File(id, DISK_ID, folderName, folderParentId, parentIdsBuffer, TableKind.FOLDER);
        fileService.save(folder);

        return Result.OK();
    }

    /**
     * 重命名文件夹
     *
     * {
     *   "id": "文件夹ID",
     *   "folderName": "文件夹名称"
     * }
     * @param vo
     * @return
     */
    @ApiOperation(value="重命名文件夹")
    @PutMapping(value = "/rename")
    public Result<?> rename(@RequestBody FolderVO vo) {

        String id = vo.getId();

        File file = FileMapping.INSTANCE.toModel(vo);
        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id", id)
                .eq("table_kind", TableKind.FOLDER)
                .eq("disk_id", DISK_ID)
                .eq("del_flag", "0");
        fileService.update(file, wrapper);

        return Result.OK();
    }






}
